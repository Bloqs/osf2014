package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.to.PublicBadgeTO;
import javax.ejb.Stateless;

/**
 *
 * @author Khaled Basbous
 */
@Stateless
public class BadgesTOService implements BadgesTOServiceLocal {

    @Override
    public PublicBadgeTO buildPublicBadgeTO(Badge source) {
        return new PublicBadgeTO(source.getId(), source.getName(), source.getDescription(), source.getIcon().toString());
    }

    @Override
    public void updateBadgeEntity(Badge existingEntity, PublicBadgeTO newState) {
        existingEntity.setName(newState.getName());
        existingEntity.setDescription(newState.getDescription());
        existingEntity.setIcon(newState.getIcon());
    }

}
