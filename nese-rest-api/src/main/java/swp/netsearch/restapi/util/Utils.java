package swp.netsearch.restapi.util;

/**
 * Created on 29.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class Utils {

    public static boolean validateMac(String mac) {
        var regex = "^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$"; //https://stackoverflow.com/a/4260512/5687665
        return mac.matches(regex);
    }

    public static boolean validateIp(String ip) {
        var regex = "^(?:(?:^|\\.)(?:2(?:5[0-5]|[0-4]\\d)|1?\\d?\\d)){4}$"; //https://www.regextester.com/95309
        return ip.matches(regex);
    }

    public static String intToIp(long ip) {
        return String.format("%d.%d.%d.%d",
                (ip >> 24 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 8 & 0xff),
                (ip & 0xff));
    }

    public static long ipToInt(String ip) {
        var parts = ip.split("\\.");
        return (Long.parseLong(parts[0]) << 24) +
                (Long.parseLong(parts[1]) << 16) +
                (Long.parseLong(parts[2]) << 8) +
                (Long.parseLong(parts[3]));
    }
}
