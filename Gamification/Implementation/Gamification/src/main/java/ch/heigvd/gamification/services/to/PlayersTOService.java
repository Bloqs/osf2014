package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.to.PublicBadgeTO;
import ch.heigvd.gamification.to.PublicPlayerTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Khaled Basbous
 */
@Stateless
public class PlayersTOService implements PlayersTOServiceLocal {

    @EJB
    ApplicationsManagerLocal applicationsManager;
    
    @EJB
    ApplicationsTOServiceLocal applicationsTOService;
    
    @EJB
    BadgesTOServiceLocal badgesTOService;
    
    @Override
    public PublicPlayerTO buildPublicPlayerTO(Player source) {
        List<PublicBadgeTO> publicBadges = new LinkedList<>();
        for (Badge badge : source.getBadges()) {
            publicBadges.add(badgesTOService.buildPublicBadgeTO(badge));
        }
        
        PublicPlayerTO to = new PublicPlayerTO(source.getId() , source.getFirstName(), source.getLastName(), source.getEmail(), source.getNumberOfPoints(), publicBadges/*, applicationsTOService.buildPublicApplicationTO(source.getApplication())*/);
        return to;
    }
    
    
    @Override
    public PublicPlayerTO buildPublicSummaryPlayerTO(String name, String lastName, Integer points){
        return new PublicPlayerTO(null , name, lastName, null, points, null);
    }

    @Override
    public void updatePlayerEntity(Player existingEntity, PublicPlayerTO newState) {
        existingEntity.setFirstName(newState.getFirstName());
        existingEntity.setLastName(newState.getLastName());
        existingEntity.setEmail(newState.getEmail());
        existingEntity.setNumberOfPoints(newState.getNumberOfPoints());
    }

}
