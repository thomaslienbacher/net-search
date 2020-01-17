package swp.netsearch.restapi.util;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class MacWithPort {
    public String mac;
    public int port;

    public MacWithPort(String mac, int port) {
        this.mac = mac;
        this.port = port;
    }

    @Override
    public String toString() {
        return "MacWithPort{" +
                "mac='" + mac + '\'' +
                ", port=" + port +
                '}';
    }
}
