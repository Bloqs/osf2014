/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.to.PublicEventTO;
import javax.ejb.Stateless;

/**
 *
 * @author Jean-Luc
 */
@Stateless
public class EventsTOService  implements EventsTOServiceLocal {
    
    @Override
    public PublicEventTO buildPublicEventTo(Event source)
    {
        ApplicationsTOService appTO = new ApplicationsTOService();
        PlayersTOService playerTO = new PlayersTOService();
        PublicEventTO event = new PublicEventTO(
                source.getType(), 
                appTO.buildPublicApplicationTO(source.getApplication()), 
                playerTO.buildPublicPlayerTO(source.getPlayer())
        );
        return event;
    }
    
    @Override
    public void updateEventEntity(Event existingEntity, PublicEventTO newState)
    {
        existingEntity.setType(newState.getType());
    }
    
}
