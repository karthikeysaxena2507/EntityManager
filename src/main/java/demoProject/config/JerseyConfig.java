package demoProject.config;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        /** Will include all classes with @Path and @Provider in the package **/
        packages("demoProject");
    }
}