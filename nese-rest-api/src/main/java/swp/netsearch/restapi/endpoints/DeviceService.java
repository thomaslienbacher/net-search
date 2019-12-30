package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.Device;
import swp.netsearch.restapi.util.GenericDao;
import swp.netsearch.restapi.util.Message;
import swp.netsearch.restapi.util.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

/**
 * Created on 28.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Path("/devices")
public class DeviceService {

    private GenericDao<Device> dao;
    private Gson gson;

    public DeviceService() {
        dao = new GenericDao<>(Device.class);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    @Produces("application/json")
    public Response all() {
        dao.openSession();
        List<Device> all = dao.all();
        dao.closeSession();
        return Response.status(Status.OK).entity(gson.toJson(all)).build();
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response get(@PathParam("id") int id) {//TODO: check if all parameters have been supplied
        dao.openSession();
        Device d = dao.get(id);

        if (d == null) {
            Message m = new Message("error: no device with id " + id);
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.closeSession();
        return Response.status(Status.OK).entity(gson.toJson(d)).build();
    }

    @POST
    @Produces("application/json")
    public Response update(@QueryParam("id") int id,//TODO: check if all parameters have been supplied
                           @QueryParam("name") String name,
                           @QueryParam("mac") String mac) {
        if (!Utils.validateMac(mac)) {
            Message m = new Message("error: mac address not valid");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.openSessionTransactional();

        if (dao.get(id) == null) {
            Message m = new Message("error: no device with id " + id);
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        Device device = new Device();
        device.setId_device(id);
        device.setName(name);
        device.setMac(mac);
        dao.update(device);
        dao.closeSessionTransactional();
        return Response.status(Status.OK).entity(gson.toJson(device)).build();
    }

    @PUT
    @Produces("application/json")
    public Response insert(@QueryParam("name") String name,//TODO: check if all parameters have been supplied
                           @QueryParam("mac") String mac) {
        if (!Utils.validateMac(mac)) {
            Message m = new Message("error: mac address not valid");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        Device device = new Device();
        device.setName(name);
        device.setMac(mac);
        dao.openSessionTransactional();
        dao.insert(device);
        dao.closeSessionTransactional();
        return Response.status(Status.OK).entity(gson.toJson(device)).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {//TODO: check if all parameters have been supplied
        dao.openSessionTransactional();
        Device device = dao.get(id);

        if (device == null) {
            Message m = new Message("error: no device with id " + id);
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.delete(device);
        dao.closeSessionTransactional();
        Message m = new Message("succesfully deleted " + id);
        return Response.status(Status.OK).entity(m.toJson()).build();
    }
}
