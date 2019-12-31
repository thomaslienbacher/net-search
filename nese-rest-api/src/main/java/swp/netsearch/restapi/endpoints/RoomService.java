package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.Room;
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
@Path("/rooms")
public class RoomService {

    private GenericDao<Room> dao;
    private Gson gson;

    public RoomService() {
        dao = new GenericDao<>(Room.class);
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
            var m = new Message("error: no room with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.closeSession();
        return Response.status(Response.Status.OK).entity(gson.toJson(d)).build();
    }

    @POST
    @Produces("application/json")
    public Response update(@QueryParam("id") Integer id,
                           @QueryParam("name") String name) {
        if (id == null) {
            var m = new Message("error: id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (name == null) {
            var m = new Message("error: name wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.openSessionTransactional();

        var room = dao.get(id);
        if (room == null) {
            var m = new Message("error: no room with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        room.setName(name);
        dao.update(room);
        dao.closeSessionTransactional();
        return Response.status(Response.Status.OK).entity(gson.toJson(room)).build();
    }

    @PUT
    @Produces("application/json")
    public Response insert(@QueryParam("name") String name) {
        if (name == null) {
            var m = new Message("error: name wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        var room = new Room();
        room.setName(name);
        dao.openSessionTransactional();
        dao.insert(room);
        dao.closeSessionTransactional();
        return Response.status(Response.Status.OK).entity(gson.toJson(room)).build();
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
        var room = dao.get(id);

        if (room == null) {
            var m = new Message("error: no room with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.delete(room);

        try {
            dao.closeSessionTransactional();
        } catch (PersistenceException e) {
            var m = new Message("error: couldn't delete room with id " + id + " because it's used in a port connection");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        var m = new Message("succesfully deleted " + id);
        return Response.status(Response.Status.OK).entity(m.toJson()).build();
    }
}
