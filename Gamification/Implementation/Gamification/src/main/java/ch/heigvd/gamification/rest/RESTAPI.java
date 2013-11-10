package ch.heigvd.gamification.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;

/**
 *
 * @author Khaled Basbous
 */
@ApplicationPath("/api")
public class RESTAPI extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resources/providers
        classes.add(GamificationExceptionMapper.class);
        classes.add(ApplicationResource.class);
        classes.add(PlayerResource.class);
        classes.add(EventResource.class);
        classes.add(RuleResource.class);
        classes.add(LeaderBoardResource.class);
        classes.add(BadgeResource.class);
        return classes;
    }
}
