package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.Device;
import swp.netsearch.restapi.models.PortConnection;
import swp.netsearch.restapi.models.Room;
import swp.netsearch.restapi.models.Switch;
import swp.netsearch.restapi.util.GenericDao;
import swp.netsearch.restapi.util.Pair;
import swp.netsearch.restapi.util.SnmpHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created on 16.01.2020.
 *
 * @author Thomas Lienbacher
 */
@Path("/search")
@Deprecated
public class SearchService {

    private GenericDao<Device> devices;
    private GenericDao<Switch> switches;
    private GenericDao<PortConnection> portConnections;
    private GenericDao<Room> rooms;
    private Gson gson;

    public SearchService() {
        devices = new GenericDao<>(Device.class);
        switches = new GenericDao<>(Switch.class);
        portConnections = new GenericDao<>(PortConnection.class);
        rooms = new GenericDao<>(Room.class);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    public Response search() {
        devices.openSession();
        switches.openSession();
        portConnections.openSession();
        rooms.openSession();

        var allDevices = devices.all();
        var devicesInRooms = new ArrayList<Pair<Device, Room>>();

        for (var pc : portConnections.all()) {
            var sw = switches.get(pc.getSwitch_id());
            var connectedDevices = new SnmpHandler().getConnectedDevices(sw);

            for (var cd : connectedDevices) {
                if (cd.b == pc.getPort()) {
                    System.out.println(cd + " : " + pc);
                    var room = rooms.get(pc.getRoom_id());
                    Device device = null;

                    for (var d : allDevices) {
                        if (d.getMac().equals(cd.a)) {
                            device = d;
                            break;
                        }
                    }

                    if (device != null) devicesInRooms.add(new Pair(device, room));
                }
            }
        }

        rooms.closeSession();
        devices.closeSession();
        switches.closeSession();
        portConnections.closeSession();

        return Response.status(Response.Status.OK).entity(gson.toJson(devicesInRooms)).build();
    }
}
