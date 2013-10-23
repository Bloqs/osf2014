package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.to.PublicApplicationTO;
import javax.ejb.Stateless;

/**
 *
 * @author Khaled Basbous
 */
@Stateless
public class ApplicationsTOService implements ApplicationsTOServiceLocal {

    @Override
    public PublicApplicationTO buildPublicApplicationTO(Application source) {
        PublicApplicationTO to = new PublicApplicationTO(source.getId(), source.getAppName(), source.getAppDescription());
        return to;
    }

    @Override
    public void updateApplicationEntity(Application existingEntity, PublicApplicationTO newState) {
        existingEntity.setAppName(newState.getName());
        existingEntity.setAppDescription(newState.getDescription());
    }

}
