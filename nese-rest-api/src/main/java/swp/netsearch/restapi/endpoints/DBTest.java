package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import swp.netsearch.restapi.models.*;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class DBTest extends ServerResource {

    @Get
    public String accept() {
        var switches = Database.session().createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        var devices = Database.session().createQuery("SELECT T FROM Device T", Device.class).getResultList();
        var rooms = Database.session().createQuery("SELECT T FROM Room T", Room.class).getResultList();
        var connections = Database.session().createQuery("SELECT T FROM AssignedConnection T", AssignedConnection.class).getResultList();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(switches) + "\n" +
                gson.toJson(devices) + "\n" +
                gson.toJson(rooms) + "\n" +
                gson.toJson(connections);
    }

}
