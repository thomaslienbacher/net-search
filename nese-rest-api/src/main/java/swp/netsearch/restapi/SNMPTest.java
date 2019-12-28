package swp.netsearch.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.Database;
import swp.netsearch.restapi.models.Switch;
import swp.netsearch.restapi.util.Pair;
import swp.netsearch.restapi.util.SNMPHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created on 05.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Path("/snmptest")
public class SNMPTest {

    @GET
    public Response accept() {
        Database.init();
        List<Switch> switches = Database.session().createQuery("SELECT T FROM Switch T", Switch.class).getResultList();
        List<Pair<String, Integer>> devs = new SNMPHandler().getAllConnectedDevices(switches);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String output = gson.toJson(devs);
        return Response.status(200).entity(output).build();
    }
}
