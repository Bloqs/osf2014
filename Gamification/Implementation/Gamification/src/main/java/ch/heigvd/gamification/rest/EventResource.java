/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.AuthentificationFailedException;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.services.crud.BadgesManagerLocal;
import ch.heigvd.gamification.services.crud.EventsManagerLocal;
import ch.heigvd.gamification.services.crud.PlayersManagerLocal;
import ch.heigvd.gamification.services.crud.RulesManagerLocal;
import ch.heigvd.gamification.services.to.EventsTOServiceLocal;
import ch.heigvd.gamification.to.PublicEventTO;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Jean-Luc
 */
@Stateless
@Path("applications/{apiKey}/{apiSecret}/players/{playerId}/events")
public class EventResource {

    @Context
    private UriInfo context;

    @EJB
    EventsManagerLocal eventsManager;

    @EJB
    EventsTOServiceLocal eventsTOService;

    @EJB
    ApplicationsManagerLocal applicationsManager;

    @EJB
    PlayersManagerLocal playersManager;

    @EJB
    BadgesManagerLocal badgesManager;

    @EJB
    RulesManagerLocal rulesManager;

    public EventResource() {
    }

    @POST
    @Consumes({"application/json"})
    public Response createResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId, PublicEventTO newEventTO) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        Rule rule = rulesManager.findByType(newEventTO.getType());
        Event newEvent = new Event();
        Date date = new java.util.Date();
        newEvent.setTimeEvent(new Timestamp(date.getTime()).getTime());
        newEvent.setApplication(app);
        newEvent.setPlayer(play);
        eventsTOService.updateEventEntity(newEvent, newEventTO);
        long newEventId = eventsManager.create(newEvent);
        play.getEvents().add(eventsManager.findById(newEventId));
        if (rule.getBadge() != null) {
            play.getBadges().add(rule.getBadge());
        }
        play.setNumberOfPoints(play.getNumberOfPoints() + rule.getNumberOfPoints());
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newEventId)).build();
        return Response.created(createdURI).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PublicEventTO> getResourceList(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        List<Event> events = new ArrayList<>(play.getEvents());
        List<PublicEventTO> result = new LinkedList<>();
        for (Event event : events) {
            result.add(eventsTOService.buildPublicEventTO(event));
        }
        return result;
    }

    @GET
    @Path("{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PublicEventTO getResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId, @PathParam("eventId") long eventId) throws EntityNotFoundException, AuthentificationFailedException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        Event event = eventsManager.findById(eventId);
        System.out.println(play.getEvents().contains(event));
        if (play.getEvents().contains(event)) {
            PublicEventTO eventTO = eventsTOService.buildPublicEventTO(event);
            return eventTO;
        }
        return new PublicEventTO();
    }

    /* Non necessaire pour gamification engine
     @PUT
     @Path("{eventId}")
     @Consumes({"application/json"})
     public Response Resource(PublicEventTO updatedEventTO, @PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId, @PathParam("eventId") long eventId) throws EntityNotFoundException, AuthentificationFailedException {
     Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
     Player play = playersManager.findById(playerId);
     Event eventToUpdate = eventsManager.findById(eventId);
     if (play.getEvents().contains(eventToUpdate)) {
     eventsTOService.updateEventEntity(eventToUpdate, updatedEventTO);
     eventsManager.update(eventToUpdate);
     return Response.ok().build();
     }
     return Response.notModified().build();
     }
     */
    @DELETE
    @Path("{eventId}")
    public Response deleteResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId, @PathParam("eventId") long eventId) throws EntityNotFoundException, AuthentificationFailedException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        Event eventToDelete = eventsManager.findById(eventId);
        if (play.getEvents().contains(eventToDelete)) {
            play.getEvents().remove(eventToDelete);
            eventsManager.delete(eventId);
            Rule rule = rulesManager.findByType(eventToDelete.getType());
            if (rule.getBadge() != null) {
                play.getBadges().remove(rule.getBadge());
            }
            play.setNumberOfPoints(play.getNumberOfPoints() - rule.getNumberOfPoints());
            return Response.ok().build();
        }
        return Response.notModified().build();
    }
}
