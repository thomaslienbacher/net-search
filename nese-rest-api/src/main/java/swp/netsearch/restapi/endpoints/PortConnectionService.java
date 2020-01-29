package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.exception.ConstraintViolationException;
import swp.netsearch.restapi.models.PortConnection;
import swp.netsearch.restapi.util.GenericDao;
import swp.netsearch.restapi.util.Message;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created on 31.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Path("/portconnections")
public class PortConnectionService {

    private GenericDao<PortConnection> dao;
    private Gson gson;

    public PortConnectionService() {
        dao = new GenericDao<>(PortConnection.class);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    @Produces("application/json")
    public Response all() {
        dao.openSession();
        var all = dao.all();
        dao.closeSession();
        return Response.status(Response.Status.OK).entity(gson.toJson(all)).build();
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
            var m = new Message("error: no port_connection with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.closeSession();
        return Response.status(Response.Status.OK).entity(gson.toJson(d)).build();
    }

    @PUT
    @Produces("application/json")
    public Response update(@QueryParam("id") Integer id,
                           @QueryParam("switch_id") Integer switch_id,
                           @QueryParam("room_id") Integer room_id,
                           @QueryParam("port") Integer port) {
        if (id == null) {
            var m = new Message("error: id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (switch_id == null) {
            var m = new Message("error: switch_id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (room_id == null) {
            var m = new Message("error: room_id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (port == null) {
            var m = new Message("error: port wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.openSessionTransactional();

        var portConnection = dao.get(id);
        if (portConnection == null) {
            var m = new Message("error: no port_connection with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        portConnection.setSwitch_id(switch_id);
        portConnection.setRoom_id(room_id);
        portConnection.setPort(port);
        dao.update(portConnection);

        try {
            dao.closeSessionTransactional();
        } catch (PersistenceException e) {
            var m = new Message("error: couldn't update port_connection (" + e.getClass().getName() + ")");
            var s = e.getCause().getCause().getMessage();

            if (s.contains("switch")) {
                m.setMessage("error: couldn't add port_connection because switch with id " + switch_id + " doesn't exist");
            }
            if (s.contains("room")) {
                m.setMessage("error: couldn't add port_connection because room with id " + room_id + " doesn't exist");
            }

            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        return Response.status(Response.Status.OK).entity(gson.toJson(portConnection)).build();
    }

    @POST
    @Produces("application/json")
    public Response insert(@QueryParam("switch_id") Integer switch_id,
                           @QueryParam("room_id") Integer room_id,
                           @QueryParam("port") Integer port) {
        if (switch_id == null) {
            var m = new Message("error: switch_id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (room_id == null) {
            var m = new Message("error: room_id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (port == null) {
            var m = new Message("error: port wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        var portConnection = new PortConnection();
        portConnection.setSwitch_id(switch_id);
        portConnection.setRoom_id(room_id);
        portConnection.setPort(port);
        dao.openSessionTransactional();

        try {
            dao.insert(portConnection);
        } catch (ConstraintViolationException e) {
            var m = new Message("error: couldn't add port_connection (" + e.getClass().getName() + ")");
            var s = e.getCause().getMessage();

            if (s.contains("switch")) {
                m.setMessage("error: couldn't add port_connection because switch with id " + switch_id + " doesn't exist");
            }
            if (s.contains("room")) {
                m.setMessage("error: couldn't add port_connection because room with id " + room_id + " doesn't exist");
            }

            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.closeSessionTransactional();

        return Response.status(Response.Status.OK).entity(gson.toJson(portConnection)).build();
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
        var portConnection = dao.get(id);

        if (portConnection == null) {
            var m = new Message("error: no port_connection with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.delete(portConnection);
        dao.closeSessionTransactional();
        var m = new Message("succesfully deleted " + id);
        return Response.status(Response.Status.OK).entity(m.toJson()).build();
    }
}
