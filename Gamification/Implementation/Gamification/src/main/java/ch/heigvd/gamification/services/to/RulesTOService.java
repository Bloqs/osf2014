/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.to.PublicRuleTO;
import javax.ejb.Stateless;

/**
 *
 * @author Jean-Luc
 */
@Stateless
public class RulesTOService implements RulesTOServiceLocal 
{
    
    @Override
    public PublicRuleTO buildPublicRuleTo (Rule source)
    {
        ApplicationsTOService appTO = new ApplicationsTOService();
        PublicRuleTO rule = new PublicRuleTO(
                source.getId(),
                source.getOnEventType(),
                source.getNumberOfPoints(),
                appTO.buildPublicApplicationTO(source.getApplication())
        );
        return rule;
    }
    
    @Override
    public void updateRuleEntity(Rule existingEntity, PublicRuleTO newState)
    {
        existingEntity.setOnEventType(newState.getOnEventType());
        existingEntity.setNumberOfPoints(newState.getNumberOfPoints());
    }
    
}
