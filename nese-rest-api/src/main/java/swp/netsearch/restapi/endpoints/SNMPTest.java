package swp.netsearch.restapi.endpoints;

import com.google.gson.GsonBuilder;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import swp.netsearch.restapi.models.Database;
import swp.netsearch.restapi.models.Switch;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class SNMPTest extends ServerResource {

    @Get
    public String accept() {
        var switches = Database.session().createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        var devs = new SNMPHandler().getAllConnectedDevices(switches);
        var gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(devs);
    }
}
