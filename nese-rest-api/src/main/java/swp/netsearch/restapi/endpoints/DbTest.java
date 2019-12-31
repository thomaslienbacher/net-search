package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Path("/dbtest")
@Deprecated
public class DbTest {

    @GET
    @Produces("application/json")
    public Response accept() {
        Database.init();
        List switches = Database.session().createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        List devices = Database.session().createQuery("SELECT T FROM Device T", Device.class).getResultList();
        List rooms = Database.session().createQuery("SELECT T FROM Room T", Room.class).getResultList();
        List connections = Database.session().createQuery("SELECT T FROM PortConnection T", PortConnection.class).getResultList();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String output = gson.toJson(switches) + "\n" +
                gson.toJson(devices) + "\n" +
                gson.toJson(rooms) + "\n" +
                gson.toJson(connections);
        return Response.status(200).entity(output).build();
    }

}
