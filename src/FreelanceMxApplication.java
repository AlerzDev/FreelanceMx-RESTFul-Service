import endpoints.TypeUserEndPoint;
import endpoints.UserFreelanceEndPoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api_v1")
public class FreelanceMxApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(TypeUserEndPoint.class);
        h.add(UserFreelanceEndPoint.class);
        return h;
    }
}
