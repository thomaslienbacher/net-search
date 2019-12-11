package swp.netsearch.restapi;

import org.restlet.Component;
import org.restlet.data.Protocol;
import swp.netsearch.restapi.endpoints.DBTest;
import swp.netsearch.restapi.endpoints.DeviceAPI;
import swp.netsearch.restapi.endpoints.SNMPTest;
import swp.netsearch.restapi.models.Database;

/**
 * Created on 29.11.2019.
 *
 * @author Thomas Lienbacher
 */
public class Main {
    public static void main(String[] args) {
        Database.init();

        var component = new Component();
        component.getServers().add(Protocol.HTTP, 8100);
        component.getDefaultHost().attach("/snmptest", SNMPTest.class);
        component.getDefaultHost().attach("/dbtest", DBTest.class);

        try {
            component.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Database.close();
    }
}
