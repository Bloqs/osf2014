/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.services.to;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.to.PublicEventTO;
import javax.ejb.Local;

/**
 *
 * @author Jean-Luc
 */
@Local
public interface EventsTOServiceLocal {
    public PublicEventTO buildPublicEventTO(Event source);
    public void updateEventEntity(Event existingEntity, PublicEventTO newState);
}
