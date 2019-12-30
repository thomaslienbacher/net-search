package swp.netsearch.restapi.exceptions;

import swp.netsearch.restapi.util.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created on 29.12.2019.
 *
 * @author Thomas Lienbacher
 */
@Provider
public class ErrorMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            WebApplicationException e = (WebApplicationException) exception;
            Response r = e.getResponse();
            Response.Status status = Response.Status.fromStatusCode(r.getStatus());
            Message m = new Message("error: " + status.getStatusCode() + " " + status.getReasonPhrase().toLowerCase());
            return Response.status(r.getStatus()).entity(m.toJson()).type(MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            Message m = new Message("error: 500 internal server error");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(m.toJson()).type(MediaType.APPLICATION_JSON_TYPE).build();
        }
    }
}