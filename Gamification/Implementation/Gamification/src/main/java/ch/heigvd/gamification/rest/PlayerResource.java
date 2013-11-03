package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.AuthentificationFailedException;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.services.crud.PlayersManagerLocal;
import ch.heigvd.gamification.services.to.PlayersTOServiceLocal;
import ch.heigvd.gamification.to.PublicPlayerTO;
import java.net.URI;
import java.util.ArrayList;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Khaled Basbous
 */
@Stateless
@Path("applications/{apiKey}/{apiSecret}/players")
public class PlayerResource {

    @Context
    private UriInfo context;

    @EJB
    PlayersManagerLocal playersManager;

    @EJB
    PlayersTOServiceLocal playersTOService;

    @EJB
    ApplicationsManagerLocal applicationsManager;

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
    public Response createResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, PublicPlayerTO newPlayerTO) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player newPlayer = new Player();
        playersTOService.updatePlayerEntity(newPlayer, newPlayerTO);
        newPlayer.setApplication(app);
        long newPlayerId = playersManager.create(newPlayer);
        app.getPlayers().add(playersManager.findById(newPlayerId));
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newPlayerId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Application resources
     *
     * @return a list of PublicApplicationTO instances
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PublicPlayerTO> getResourceList(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        List<Player> players = new ArrayList<Player>(app.getPlayers());
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
    @Path("{playerId}")
    @Produces({"application/json", "application/xml"})
    public PublicPlayerTO getResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") long playerId) throws EntityNotFoundException, AuthentificationFailedException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player player = playersManager.findById(playerId);
        if (app.getPlayers().contains(player)) {
            PublicPlayerTO playerTO = playersTOService.buildPublicPlayerTO(player);
            return playerTO;
        }
        return new PublicPlayerTO();
    }

    /**
     * Updates an Application resource
     *
     * @return an instance of PublicApplicationTO
     */
    @PUT
    @Path("{playerId}")
    @Consumes({"application/json"})
    public Response Resource(PublicPlayerTO updatedPlayerTO, @PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") long playerId) throws EntityNotFoundException, AuthentificationFailedException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player playerToUpdate = playersManager.findById(playerId);
        if (app.getPlayers().contains(playerToUpdate)) {
            playersTOService.updatePlayerEntity(playerToUpdate, updatedPlayerTO);
            playersManager.update(playerToUpdate);
            return Response.ok().build();
        }
        return Response.notModified().build();
    }

    /**
     * Deletes an Application resource
     *
     * @return an instance of PublicApplicationTO
     */
    @DELETE
    @Path("{playerId}")
    public Response deleteResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret, @PathParam("playerId") long playerId) throws EntityNotFoundException, AuthentificationFailedException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        Player playerToDelete = playersManager.findById(playerId);
        if (app.getPlayers().contains(playerToDelete)) {
            app.getPlayers().remove(playerToDelete);
            playersManager.delete(playerId);
            return Response.ok().build();
        }
        return Response.notModified().build();
    }

}
