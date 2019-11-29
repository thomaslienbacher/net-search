package swp.netsearch.restapi;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Main extends ServerResource {
    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8100);
        component.getDefaultHost().attach("/v1", Main.class);
        component.start();
    }

    @Get
    public String rand() {
        return "rand: " + Math.random();
    }
}
