package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.AuthentificationFailedException;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Player;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.services.crud.PlayersManagerLocal;
import ch.heigvd.gamification.services.to.ApplicationsTOServiceLocal;
import ch.heigvd.gamification.services.to.PlayersTOServiceLocal;
import ch.heigvd.gamification.to.PublicLeaderBoardTO;
import ch.heigvd.gamification.to.PublicPlayerTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Khaled Basbous
 */
@Stateless
@Path("applications/{apiKey}/{apiSecret}/leaderboard")
public class LeaderBoardResource {

    @EJB
    PlayersManagerLocal playersManager;

    @EJB
    PlayersTOServiceLocal playersTOService;
    
    @EJB
    ApplicationsManagerLocal applicationsManager;

    @EJB
    ApplicationsTOServiceLocal applicationsTOService;


    /**
     * Creates a new instance of ApplicationsResource
     */
    public LeaderBoardResource() {
    }

    /**
     * Retrieves a representation of a list of Application resources
     *
     * @param apiKey
     * @param apiSecret
     * @return a list of PublicApplicationTO instances
     * @throws ch.heigvd.gamification.exceptions.AuthentificationFailedException
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PublicLeaderBoardTO getResourceList(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret) throws AuthentificationFailedException, EntityNotFoundException {
        Application app = applicationsManager.checkApiSecret(apiKey, apiSecret);
        List<Player> players = new ArrayList<>(playersManager.findLeader(apiKey));
        List<PublicPlayerTO> ranking = new LinkedList<>();
        for (Player player : players) {
            ranking.add(playersTOService.buildPublicSummaryPlayerTO(player.getFirstName(), player.getLastName(), player.getNumberOfPoints()));
        }
        return new PublicLeaderBoardTO(applicationsTOService.buildPublicSummaryApplicationTO(app.getName(), app.getDescription()/*new PrivateApplicationTO(null, app.getName(), app.getDescription(), null, null*/), ranking);
    }


}
