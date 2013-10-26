package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an CRUD service, which uses the JPA entity manager to interact with
 * the DB. It returns JPA entities to its clients.
 *
 * @author Khaled Basbous
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {

    @PersistenceContext(unitName = "ch.heigvd_Gamification_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public long create(Player playerData) {
        Player newPlayer = new Player(playerData);
        em.persist(newPlayer);
        return newPlayer.getId();
    }

    @Override
    public void update(Player newState) throws EntityNotFoundException {
        Player playerToUpdate = findById(newState.getId());
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Player playerToDelete = findById(id);
        em.remove(playerToDelete);
    }

    @Override
    public Player findById(long id) throws EntityNotFoundException {
        Player existingPlayer = em.find(Player.class, id);
        if (existingPlayer == null) {
            throw new EntityNotFoundException();
        }
        return existingPlayer;
    }

    @Override
    public List<Player> findAll() {
        List players = em.createNamedQuery("findAllPlayers").getResultList();
        return players;
    }

}
