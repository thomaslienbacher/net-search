package swp.netsearch.restapi.exceptions;

import org.glassfish.jersey.internal.util.Base64;
import swp.netsearch.restapi.util.Message;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created on 30.12.2019.
 *
 * @author Thomas Lienbacher
 * <p>
 * from https://howtodoinjava.com/jersey/jersey-rest-security/
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        if (authorization == null || authorization.isEmpty()) {
            var m = new Message("error: not authorized");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity((m.toJson())).build());
            return;
        }

        String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        String usernamePassword = new String(Base64.decode(encodedUserPassword.getBytes()));
        StringTokenizer tokenizer = new StringTokenizer(usernamePassword, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        if (!allowed(username, password)) {
            var m = new Message("error: not authorized");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity((m.toJson())).build());
        }
    }

    private boolean allowed(String username, String password) {
        return username.equals("admin") && password.equals("admin");//TODO: configure proper values
    }
}
