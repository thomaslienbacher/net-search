package swp.netsearch.restapi.util;

/**
 * Created on 29.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class Utils {

    //TODO: write tests
    public static boolean validateMac(String mac) {
        String regex = "^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$"; //https://stackoverflow.com/a/4260512/5687665
        return mac.matches(regex);
    }

}
