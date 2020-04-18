package swp.netsearch.restapi.exceptions;

import io.github.cdimascio.dotenv.Dotenv;
import swp.netsearch.restapi.util.Message;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * Created on 30.12.2019.
 *
 * @author Thomas Lienbacher
 * <p>
 * from https://howtodoinjava.com/jersey/jersey-rest-security/
 */
@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_KEY = "API_TOKEN";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> authorization = headers.get(AUTHORIZATION_KEY);

        if (authorization == null || authorization.isEmpty()) {
            var m = new Message("error: not authorized");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity((m.toJson())).build());
            return;
        }

        if (!allowed(authorization.get(0))) {
            var m = new Message("error: not authorized");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity((m.toJson())).build());
        }
    }

    private boolean allowed(String token) {
        Dotenv dotenv = Dotenv.load();
        String env_token = dotenv.get("API_TOKEN");
        return token.equals(env_token);
    }
}
