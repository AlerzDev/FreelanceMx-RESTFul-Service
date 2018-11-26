import endpoints.*;

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
        h.add(ProgrammingLanguageEndPoint.class);
        h.add(StatusProjectEndPoint.class);
        h.add(CommentaryEndPoint.class);
        h.add(OfferEndPoint.class);
        h.add(TypePaymentEndPoint.class);
        h.add(ProyectEndPoint.class);
        return h;
    }

}
