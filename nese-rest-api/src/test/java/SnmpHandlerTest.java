import org.junit.jupiter.api.Test;
import swp.netsearch.restapi.util.SnmpHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 30.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class SnmpHandlerTest {

    public String _dotNotationToMAC(String s) throws Exception {
        var method = SnmpHandler.class.getDeclaredMethod("dotNotationToMAC", String.class);
        method.setAccessible(true);
        var sh = new SnmpHandler();
        return (String) method.invoke(sh, s);
    }

    @Test
    public void dotNotationToMAC() throws Exception {
        assertEquals("01:01:01:01:01:01", _dotNotationToMAC("1.1.1.01.01.01"));
        assertEquals("ff:ff:ff:ff:ff:ff", _dotNotationToMAC("255.255.00255.255.255.255"));
        assertEquals("01:09:0a:0f:10:01", _dotNotationToMAC("1.9.10.15.16.1"));
    }
}
