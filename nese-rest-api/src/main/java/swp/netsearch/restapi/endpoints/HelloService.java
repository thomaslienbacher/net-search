package swp.netsearch.restapi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created on 28.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Path("/hello")
public class HelloService {

    @GET
    public Response helloWorld() {
        String output = "Hello world!";
        return Response.status(200).entity(output).build();
    }
}
