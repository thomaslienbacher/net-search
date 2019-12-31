package swp.netsearch.restapi.exceptions;

import swp.netsearch.restapi.util.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created on 29.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Provider //TODO: uncomment to activate, is deactivated for better debugging
public class ErrorMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            WebApplicationException e = (WebApplicationException) exception;
            var r = e.getResponse();
            var status = Response.Status.fromStatusCode(r.getStatus());
            var m = new Message("error: " + status.getStatusCode() + " " + status.getReasonPhrase().toLowerCase() + " (" + exception.getClass().getName() + ")");
            return Response.status(r.getStatus()).entity(m.toJson()).type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        var m = new Message("error: 500 internal server error (" + exception.getClass().getName() + ")");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(m.toJson()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}