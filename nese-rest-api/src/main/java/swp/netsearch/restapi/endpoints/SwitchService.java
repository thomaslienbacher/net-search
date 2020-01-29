package swp.netsearch.restapi.endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import swp.netsearch.restapi.models.Switch;
import swp.netsearch.restapi.util.GenericDao;
import swp.netsearch.restapi.util.Message;
import swp.netsearch.restapi.util.Utils;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created on 31.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Path("/switches")
public class SwitchService {

    private GenericDao<Switch> dao;
    private Gson gson;

    public SwitchService() {
        dao = new GenericDao<>(Switch.class);
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
            var m = new Message("error: no switch with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.closeSession();
        return Response.status(Response.Status.OK).entity(gson.toJson(d)).build();
    }

    @PUT
    @Produces("application/json")
    public Response update(@QueryParam("id") Integer id,
                           @QueryParam("name") String name,
                           @QueryParam("ip") String ip,
                           @QueryParam("community_string") String community_string) {
        if (id == null) {
            var m = new Message("error: id wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (name == null) {
            var m = new Message("error: name wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (ip == null) {
            var m = new Message("error: ip wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (community_string == null) {
            var m = new Message("error: community_string wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (!Utils.validateIp(ip)) {
            var m = new Message("error: ip address not valid");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.openSessionTransactional();

        var _switch = dao.get(id);
        if (_switch == null) {
            var m = new Message("error: no switch with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        _switch.setName(name);
        _switch.setIp(ip);
        _switch.setCommunity_string(community_string);
        dao.update(_switch);
        dao.closeSessionTransactional();
        return Response.status(Response.Status.OK).entity(gson.toJson(_switch)).build();
    }

    @POST
    @Produces("application/json")
    public Response insert(@QueryParam("name") String name,
                           @QueryParam("ip") String ip,
                           @QueryParam("community_string") String community_string) {
        if (name == null) {
            var m = new Message("error: name wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (ip == null) {
            var m = new Message("error: ip wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (community_string == null) {
            var m = new Message("error: community_string wasn't supplied");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        if (!Utils.validateIp(ip)) {
            var m = new Message("error: ip address not valid");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        var _switch = new Switch();
        _switch.setName(name);
        _switch.setIp(ip);
        _switch.setCommunity_string(community_string);
        dao.openSessionTransactional();
        dao.insert(_switch);
        dao.closeSessionTransactional();
        return Response.status(Response.Status.OK).entity(gson.toJson(_switch)).build();
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
        var _switch = dao.get(id);

        if (_switch == null) {
            var m = new Message("error: no switch with id " + id);
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        dao.delete(_switch);

        try {
            dao.closeSessionTransactional();
        } catch (PersistenceException e) {
            var m = new Message("error: couldn't delete switch with id " + id + " because it's used in a port connection");
            return Response.status(Response.Status.BAD_REQUEST).entity(m.toJson()).build();
        }

        var m = new Message("succesfully deleted " + id);
        return Response.status(Response.Status.OK).entity(m.toJson()).build();
    }
}
