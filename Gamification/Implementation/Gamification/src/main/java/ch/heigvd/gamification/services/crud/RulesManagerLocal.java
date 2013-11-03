/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Rule;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jean-Luc
 */
@Local
public interface RulesManagerLocal {
    
    long create(Rule ruleData);

    void update(Rule newState) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    Rule findById(long id) throws EntityNotFoundException;

    List<Rule> findAll();
}
