package swp.netsearch.restapi.endpoints;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created on 04.12.2019.
 *
 * @author Thomas Lienbacher
 */
public abstract class Endpoint extends ServerResource {

    @Get
    public String accept() {
        return "undefined open endpoint";
    }
}
