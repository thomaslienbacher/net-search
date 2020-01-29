package swp.netsearch.restapi.util;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import swp.netsearch.restapi.models.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */
public class SnmpHandler {

    /*
     * This may only work on CISCO SF 300-24 24-Port 10/100 Managed Switch
     * TODO: ? Solution: store in database
     */
    static final String OID_PORTS = ".1.3.6.1.2.1.17.4.3.1.2";

    public ArrayList<MacWithPort> getAllConnectedDevices(List<Switch> switches) {
        ArrayList<MacWithPort> all = new ArrayList<>();

        for (Switch s : switches) {
            List l = getConnectedDevices(s);
            all.addAll(l);
        }

        return all;
    }

    // returns null on error
    public ArrayList<MacWithPort> getConnectedDevices(Switch s) {
        //TODO: remove, only for testing without switch
        //return new ArrayList<>(List.of(new MacWithPort("00:00:00:00:00:00".toLowerCase(), 0), new MacWithPort("1C:1B:0D:96:C6:6D".toLowerCase(), 1)));

        ArrayList<MacWithPort> devices = new ArrayList<>();
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(s.getCommunity_string()));

        target.setAddress(GenericAddress.parse("udp:" + s.getIp() + "/161"));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);

        Map<String, String> result = null;

        try {
            result = doWalk(OID_PORTS, target);
        } catch (Exception e) {
            return null;
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            String mac = dotNotationToMAC(
                    entry.getKey().substring(OID_PORTS.length() + 1));
            int port = Integer.parseInt(entry.getValue());
            devices.add(new MacWithPort(mac, port));
        }

        return devices;
    }

    private String dotNotationToMAC(String dotNotation) {
        String[] parts = dotNotation.split("\\.");
        StringBuilder mac = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            mac.append(String.format("%02X", Integer.parseInt(parts[i])));
            if (i < parts.length - 1) mac.append(":");
        }

        return mac.toString().toLowerCase();
    }

    private Map<String, String> doWalk(String tableOid, Target target) throws Exception {
        Map<String, String> result = new TreeMap<>();
        TransportMapping<? extends Address> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();

        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(target, new OID(tableOid));
        if (events == null || events.size() == 0) {
            System.err.println("Error: Unable to read table...");//TODO: proper error handling
            throw new Exception("Error: Unable to read table...");
        }

        for (TreeEvent event : events) {
            if (event == null) {
                continue;
            }
            if (event.isError()) {
                System.err.println("Error: table OID [" + tableOid + "] " + event.getErrorMessage());//TODO: proper error handling
                throw new Exception("Error: table OID [" + tableOid + "] " + event.getErrorMessage());
                //continue;
            }

            VariableBinding[] varBindings = event.getVariableBindings();
            if (varBindings == null || varBindings.length == 0) {
                continue;
            }
            for (VariableBinding varBinding : varBindings) {
                if (varBinding == null) {
                    continue;
                }

                result.put("." + varBinding.getOid().toString(), varBinding.getVariable().toString());
            }

        }
        snmp.close();

        return result;
    }
}
