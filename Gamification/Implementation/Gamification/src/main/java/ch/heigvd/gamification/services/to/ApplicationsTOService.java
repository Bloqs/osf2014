package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.to.PrivateApplicationTO;
import ch.heigvd.gamification.to.PublicApplicationTO;
import ch.heigvd.gamification.to.PublicPlayerTO;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Khaled Basbous
 */
@Stateless
public class ApplicationsTOService implements ApplicationsTOServiceLocal {

    @EJB
    PlayersTOServiceLocal playersTOService;
    
    @Override
    public PublicApplicationTO buildPublicApplicationTO(Application source) {
        List<PublicPlayerTO> publicPlayers = new LinkedList<PublicPlayerTO>();
        for (Player player : source.getPlayers()) {
            publicPlayers.add(playersTOService.buildPublicPlayerTO(player));
        }
        
        PublicApplicationTO to = new PublicApplicationTO(source.getApiKey(), source.getName(), source.getDescription(), publicPlayers);
        return to;
    }

    @Override
    public PublicApplicationTO buildPublicSummaryApplicationTO(String name, String description){
        return new PublicApplicationTO(null, name, description, null);
    }
    
    @Override
    public void updateApplicationEntity(Application existingEntity, PrivateApplicationTO newState) {
        existingEntity.setName(newState.getName());
        existingEntity.setDescription(newState.getDescription());
        existingEntity.setApiSecret(newState.getApiSecret());
    }

}
