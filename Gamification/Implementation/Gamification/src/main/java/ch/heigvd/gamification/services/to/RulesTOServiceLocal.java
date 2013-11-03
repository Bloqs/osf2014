/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.to;
;

import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.to.PublicRuleTO;
import javax.ejb.Local;

/**
 *
 * @author Jean-Luc
 */
@Local
public interface RulesTOServiceLocal {
    public PublicRuleTO buildPublicRuleTo (Rule source);
    public void updateRuleEntity(Rule existingEntity, PublicRuleTO newState);
}
