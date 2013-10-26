package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.services.crud.PlayersManagerLocal;
import ch.heigvd.gamification.services.to.PlayersTOServiceLocal;
import ch.heigvd.gamification.to.PublicPlayerTO;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Khaled Basbous
 */
@Stateless
@Path("players")
public class PlayerResource {

    @Context
    private UriInfo context;

    @EJB
    PlayersManagerLocal playersManager;

    @EJB
    PlayersTOServiceLocal playersTOService;

    /**
     * Creates a new instance of ApplicationsResource
     */
    public PlayerResource() {
    }

    /**
     * Creates a new Application resource from the provided representation
     *
     * @return an instance of PublicApplicationTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicPlayerTO newPlayerTO) {
        Player newPlayer = new Player();
        playersTOService.updatePlayerEntity(newPlayer, newPlayerTO);
        long newPlayerId = playersManager.create(newPlayer);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newPlayerId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Application resources
     *
     * @return a list of PublicApplicationTO instances
     */
    @GET
    @Produces({"application/json", "application/xml"})
    public List<PublicPlayerTO> getResourceList() {
        List<Player> players = playersManager.findAll();
        List<PublicPlayerTO> result = new LinkedList<PublicPlayerTO>();
        for (Player player : players) {
            result.add(playersTOService.buildPublicPlayerTO(player));
        }
        return result;
    }

    /**
     * Retrieves representation of an Application resource
     *
     * @return an instance of PublicApplicationTO
     */
    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public PublicPlayerTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Player player = playersManager.findById(id);
        PublicPlayerTO playerTO = playersTOService.buildPublicPlayerTO(player);
        return playerTO;
    }

    /**
     * Updates an Application resource
     *
     * @return an instance of PublicApplicationTO
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicPlayerTO updatedPlayerTO, @PathParam("id") long id) throws EntityNotFoundException {
        Player playerToUpdate = playersManager.findById(id);
        playersTOService.updatePlayerEntity(playerToUpdate, updatedPlayerTO);
        playersManager.update(playerToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an Application resource
     *
     * @return an instance of PublicApplicationTO
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        playersManager.delete(id);
        return Response.ok().build();
    }

}
