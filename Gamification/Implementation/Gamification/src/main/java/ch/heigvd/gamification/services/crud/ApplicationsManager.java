package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
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
public class ApplicationsManager implements ApplicationsManagerLocal {

    @PersistenceContext(unitName = "ch.heigvd_Gamification_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public long create(Application applicationData) {
        Application newApplication = new Application(applicationData);
        em.persist(newApplication);
        return newApplication.getApiKey();
    }

    @Override
    public void update(Application newState) throws EntityNotFoundException {
        Application applicationToUpdate = findById(newState.getApiKey());
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Application applicationToDelete = findById(id);
        em.remove(applicationToDelete);
    }

    @Override
    public Application findById(long id) throws EntityNotFoundException {
        Application existingApplication = em.find(Application.class, id);
        if (existingApplication == null) {
            throw new EntityNotFoundException();
        }
        return existingApplication;
    }

    @Override
    public List<Application> findAll() {
        List applications = em.createNamedQuery("findAllApplications").getResultList();
        return applications;
    }
}
