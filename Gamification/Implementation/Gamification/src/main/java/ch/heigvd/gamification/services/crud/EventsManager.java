/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.services.crud;

import java.util.List;
import javax.ejb.Stateless;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jean-Luc
 */
@Stateless
public class EventsManager implements EventsManagerLocal {

    @PersistenceContext(unitName = "ch.heigvd_Gamification_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    public long create(Event eventData) {
        em.persist(eventData);
        return eventData.getId();
    }

    @Override
    public void update(Event newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Event toDelete = findById(id);
        em.remove(toDelete);
    }

    @Override
    public Event findById(long id) throws EntityNotFoundException {
        Event existingEvent = em.find(Event.class, id);
        if (existingEvent == null) {
            throw new EntityNotFoundException();
        }
        return existingEvent;
    }

    @Override
    public List<Event> findAll() {
        return em.createNamedQuery("findAllEvents").getResultList();
    }
    
}
