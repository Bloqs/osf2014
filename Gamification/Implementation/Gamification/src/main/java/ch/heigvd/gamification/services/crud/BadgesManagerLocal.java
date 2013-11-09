package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.Player;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Khaled Basbous
 */
@Local
public interface BadgesManagerLocal {

    long create(Badge badgeData);

    void update(Badge newState) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    Badge findById(long id) throws EntityNotFoundException;

    List<Badge> findAll();
    
}
