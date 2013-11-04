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
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.services.crud.EventsManagerLocal;
import ch.heigvd.gamification.services.crud.PlayersManagerLocal;
import ch.heigvd.gamification.services.to.EventsTOServiceLocal;
import ch.heigvd.gamification.to.PublicEventTO;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    public EventResource() {
    }

    @POST
    @Consumes({"application/json"})
    public Response createResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId, PublicEventTO newEventTO) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        Event newEvent = new Event();
        newEvent.setApplication(app);
        newEvent.setPlayer(play);
        eventsTOService.updateEventEntity(newEvent, newEventTO);
        long newEventId = eventsManager.create(newEvent);
        play.getEvents().add(eventsManager.findById(newEventId));
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newEventId)).build();
        return Response.created(createdURI).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicEventTO> getResourceList(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        List<Event> events = new ArrayList<Event>(play.getEvents());
        List<PublicEventTO> result = new LinkedList<PublicEventTO>();
        for (Event event : events) {
            result.add(eventsTOService.buildPublicEventTO(event));
        }
        return result;
    }

    @GET
    @Path("{eventId}")
    @Produces({"application/json", "application/xml"})
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

    @DELETE
    @Path("{eventId}")
    public Response deleteResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") Long playerId, @PathParam("eventId") long eventId) throws EntityNotFoundException, AuthentificationFailedException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player play = playersManager.findById(playerId);
        Event eventToDelete = eventsManager.findById(eventId);
        if (play.getEvents().contains(eventToDelete)) {
            play.getEvents().remove(eventToDelete);
            eventsManager.delete(eventId);
            return Response.ok().build();
        }
        return Response.notModified().build();
    }
}
