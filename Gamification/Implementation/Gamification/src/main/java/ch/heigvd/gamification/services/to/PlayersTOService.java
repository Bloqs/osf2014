package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.to.PublicPlayerTO;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Override
    public PublicPlayerTO buildPublicPlayerTO(Player source) {
        PublicPlayerTO to = new PublicPlayerTO(source.getId() , source.getFirstName(), source.getLastName(), source.getEmail(), source.getNumberOfPoints()/*, applicationsTOService.buildPublicApplicationTO(source.getApplication())*/);
        return to;
    }

    @Override
    public void updatePlayerEntity(Player existingEntity, PublicPlayerTO newState) {
        existingEntity.setFirstName(newState.getFirstName());
        existingEntity.setLastName(newState.getLastName());
        existingEntity.setEmail(newState.getEmail());
        existingEntity.setNumberOfPoints(newState.getNumberOfPoints());
     /*   
        try {
            existingEntity.setApplication(applicationsManager.findById(newState.getApplicationTO().getApiKey()));
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(PlayersTOService.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
