package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Player;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Khaled Basbous
 */
@Local
public interface PlayersManagerLocal {

    long create(Player applicationData);

    void update(Player newState) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    Player findById(long id) throws EntityNotFoundException;

    List<Player> findAll();

    List<Player> findLeader(String apiKey);
    
}
