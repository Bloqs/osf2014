package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Badge;
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
public class BadgesManager implements BadgesManagerLocal {

    @PersistenceContext(unitName = "ch.heigvd_Gamification_war_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Badge badgeToDelete = findById(id);
        em.remove(badgeToDelete);
    }

    @Override
    public Badge findById(long id) throws EntityNotFoundException {
        Badge existingBadge = em.find(Badge.class, id);
        if (existingBadge == null) {
            throw new EntityNotFoundException();
        }
        return existingBadge;
    }

    @Override
    public List<Badge> findAll() {
        List badges = em.createNamedQuery("findAllBadges").getResultList();
        return badges;
    }

    @Override
    public int findBadgeWithPlayers(Badge b) {
        return em.createNamedQuery("findBadgeWithPlayers").setParameter("badge", b).getResultList().size();
    }

    @Override
    public int findBadgeInRules(Badge b) {
        return em.createNamedQuery("findBadgeInRules").setParameter("badge", b).getResultList().size();
    }

    @Override
    public long create(Badge badgeData) {
        Badge newBadge = new Badge(badgeData);
        em.persist(newBadge);
        return newBadge.getId();
    }

    @Override
    public void update(Badge newState) throws EntityNotFoundException {
        Badge badgeToUpdate = findById(newState.getId());
        em.merge(newState);
    }

}
