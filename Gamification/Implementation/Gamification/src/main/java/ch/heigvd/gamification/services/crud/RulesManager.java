/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Rule;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jean-Luc
 */
@Stateless
public class RulesManager implements RulesManagerLocal {

    @PersistenceContext(unitName = "ch.heigvd_Gamification_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    public long create(Rule ruleData) {
        Rule newRule = new Rule(ruleData);
        em.persist(newRule);
        return newRule.getId();
    }

    @Override
    public void update(Rule newState) throws EntityNotFoundException {
        Rule ruleToUpdate = findById(newState.getId());
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Rule toDelete = findById(id);
        em.remove(toDelete);
    }

    @Override
    public Rule findById(long id) throws EntityNotFoundException {
        Rule existingRule = em.find(Rule.class, id);
        if (existingRule == null) {
            throw new EntityNotFoundException();
        }
        return existingRule;
    }

    @Override
    public List<Rule> findAll() {
        return em.createNamedQuery("findAllRules").getResultList();
    }
}
