package swp.netsearch.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created on 29.11.2019.
 *
 * @author Thomas Lienbacher
 */
public class Main extends ServerResource {
    public static void main(String[] args) throws Exception {
        var component = new Component();
        component.getServers().add(Protocol.HTTP, 8100);
        component.getDefaultHost().attach("/db", Main.class);
        component.getDefaultHost().attach("/snmp", SNMPHandler.class);
        component.start();
    }

    @Get
    public static String dbtest() {
        var db = new Database();
        var devices = db.session.createQuery("SELECT T FROM AssignedConnection T", AssignedConnection.class).getResultList();
        db.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(devices);
    }
}
