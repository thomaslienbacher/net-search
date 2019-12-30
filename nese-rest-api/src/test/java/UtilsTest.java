import org.junit.jupiter.api.Test;
import swp.netsearch.restapi.util.Utils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 30.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class UtilsTest {

    @Test
    public void validateMac() {
        String mac = "54:C2:45:A0:19:70";
        assertTrue(Utils.validateMac(mac));

        mac = "00:FF:ff:aa:99:11";
        assertTrue(Utils.validateMac(mac));

        mac = "11:11:0:1:0:1a";
        assertFalse(Utils.validateMac(mac));

        mac = "11:11:00:1:01:00";
        assertFalse(Utils.validateMac(mac));
    }

    @Test
    public void validateIp() {
        String ip = "128.232.3.12";
        assertTrue(Utils.validateIp(ip));

        ip = "0.0.0.0";
        assertTrue(Utils.validateIp(ip));

        ip = "255.255.255.255";
        assertTrue(Utils.validateIp(ip));

        ip = "128.112.300.1";
        assertFalse(Utils.validateIp(ip));

        ip = "0000.123.123.44";
        assertFalse(Utils.validateIp(ip));
    }

    @Test
    public void intToIp() {
        long ip = 0;
        assertEquals("0.0.0.0", Utils.intToIp(ip));

        ip = 34;
        assertEquals("0.0.0.34", Utils.intToIp(ip));

        ip = 12345;
        assertEquals("0.0.48.57", Utils.intToIp(ip));

        ip = 4194967295L;
        assertEquals("250.10.30.255", Utils.intToIp(ip));
    }

    @Test
    public void ipToInt() {
        String ip = "128.129.1.0";
        assertEquals(2155938048L, Utils.ipToInt(ip));

        ip = "0.0.0.0";
        assertEquals(0, Utils.ipToInt(ip));

        ip = "255.255.255.255";
        assertEquals(4294967295L, Utils.ipToInt(ip));
    }

}
