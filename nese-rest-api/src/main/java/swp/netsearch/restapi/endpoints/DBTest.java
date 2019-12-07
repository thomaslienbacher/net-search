package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.*;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class DBTest extends Endpoint {

    @Override
    public String accept() {
        var db = new Database();
        var switches = db.session.createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        var devices = db.session.createQuery("SELECT T FROM Device T", Device.class).getResultList();
        var rooms = db.session.createQuery("SELECT T FROM Room T", Room.class).getResultList();
        var connections = db.session.createQuery("SELECT T FROM AssignedConnection T", AssignedConnection.class).getResultList();
        db.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(switches) + "\n" +
                gson.toJson(devices) + "\n" +
                gson.toJson(rooms) + "\n" +
                gson.toJson(connections);
    }

}
