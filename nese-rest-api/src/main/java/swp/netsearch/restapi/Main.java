package swp.netsearch.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import swp.netsearch.restapi.endpoints.SNMPHandler;
import swp.netsearch.restapi.models.Database;
import swp.netsearch.restapi.models.Switch;

/**
 * Created on 29.11.2019.
 *
 * @author Thomas Lienbacher
 */
public class Main extends ServerResource {
    public static void main(String[] args) throws Exception {
        var component = new Component();
        component.getServers().add(Protocol.HTTP, 8100);
        component.getDefaultHost().attach("/snmp", SNMPHandler.class);
        component.getDefaultHost().attach("/dbtest", Main.class);
        component.start();
    }

    @Get
    public static String dbtest() {
        var db = new Database();
        var devices = db.session.createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        db.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(devices);
    }
}
