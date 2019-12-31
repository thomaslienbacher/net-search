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
        var all = dao.all();
        dao.closeSession();
        return Response.status(Status.OK).entity(gson.toJson(all)).build();
    }

    /*
     * id doesn't need to be checked because if its not there the request will be redirected and
     * if its not a number it will be interpreted as another sub path
     */
    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response get(@PathParam("id") int id) {
        dao.openSession();
        var d = dao.get(id);

        if (d == null) {
            var m = new Message("error: no device with id " + id);
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.closeSession();
        return Response.status(Status.OK).entity(gson.toJson(d)).build();
    }

    @POST
    @Produces("application/json")
    public Response update(@QueryParam("id") Integer id,
                           @QueryParam("name") String name,
                           @QueryParam("mac") String mac) {
        if (id == null) {
            var m = new Message("error: id wasn't supplied");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (name == null) {
            var m = new Message("error: name wasn't supplied");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (mac == null) {
            var m = new Message("error: mac wasn't supplied");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (!Utils.validateMac(mac)) {
            var m = new Message("error: mac address not valid");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.openSessionTransactional();

        var device = dao.get(id);
        if (device == null) {
            var m = new Message("error: no device with id " + id);
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        device.setName(name);
        device.setMac(mac);
        dao.update(device);
        dao.closeSessionTransactional();
        return Response.status(Status.OK).entity(gson.toJson(device)).build();
    }

    @PUT
    @Produces("application/json")
    public Response insert(@QueryParam("name") String name,
                           @QueryParam("mac") String mac) {
        if (name == null) {
            var m = new Message("error: name wasn't supplied");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (mac == null) {
            var m = new Message("error: mac wasn't supplied");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (!Utils.validateMac(mac)) {
            var m = new Message("error: mac address not valid");
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        var device = new Device();
        device.setName(name);
        device.setMac(mac);
        dao.openSessionTransactional();
        dao.insert(device);
        dao.closeSessionTransactional();
        return Response.status(Status.OK).entity(gson.toJson(device)).build();
    }

    /*
     * id doesn't need to be checked because if its not there the request will be redirected or
     * method not supported and if its not a number it will be interpreted as another sub path
     */
    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        dao.openSessionTransactional();
        var device = dao.get(id);

        if (device == null) {
            var m = new Message("error: no device with id " + id);
            return Response.status(Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.delete(device);
        dao.closeSessionTransactional();
        var m = new Message("succesfully deleted " + id);
        return Response.status(Status.OK).entity(m.toJson()).build();
    }
}
