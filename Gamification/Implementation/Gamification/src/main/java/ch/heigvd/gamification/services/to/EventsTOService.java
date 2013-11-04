/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.to.PublicEventTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Jean-Luc
 */
@Stateless
public class EventsTOService  implements EventsTOServiceLocal {
    
    @EJB
    ApplicationsTOServiceLocal applicationsTOService;
    
    @EJB
    PlayersTOServiceLocal playersTOService;
    
    @Override
    public PublicEventTO buildPublicEventTO(Event source)
    {
        return new PublicEventTO(source.getId(), source.getType());
    }
    
    @Override
    public void updateEventEntity(Event existingEntity, PublicEventTO newState)
    {
        existingEntity.setType(newState.getType());
    }
    
}
