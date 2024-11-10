package integrationtest;
import org.glassfish.jersey.server.ResourceConfig;

import restserver.Controller;

public class EndpointConfig extends ResourceConfig {
    public EndpointConfig() {
        register(Controller.class);
        for (int i = 0; i < 100; i++)
        {
            System.out.print("Yehhaw");
        }
    }
}
