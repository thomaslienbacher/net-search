package swp.netsearch.restapi.endpoints;

import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.Database;
import swp.netsearch.restapi.models.Switch;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class SNMPTest extends Endpoint {

    @Override
    public String accept() {
        var db = new Database();
        var switches = db.session.createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        db.close();
        var devs = new SNMPHandler().getAllConnectedDevices(switches);
        var gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(devs);
    }
}
