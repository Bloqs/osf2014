package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.services.crud.BadgesManagerLocal;
import ch.heigvd.gamification.to.PublicBadgeTO;
import ch.heigvd.gamification.services.to.BadgesTOServiceLocal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("badges")
public class BadgeResource {

    @Context
    private UriInfo context;

    @EJB
    BadgesManagerLocal badgesManager;

    @EJB
    BadgesTOServiceLocal badgesTOService;

    @EJB
    ApplicationsManagerLocal applicationsManager;

    /**
     * Creates a new instance of ApplicationsResource
     */
    public BadgeResource() {
    }

    /**
     * Creates a new Application resource from the provided representation
     *
     * @return an instance of PublicApplicationTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicBadgeTO newBadgeTO) {
        Badge newBadge = new Badge();
        badgesTOService.updateBadgeEntity(newBadge, newBadgeTO);
        long newBadgeId = badgesManager.create(newBadge);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newBadgeId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Application resources
     *
     * @return a list of PublicApplicationTO instances
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PublicBadgeTO> getResourceList() {
        List<Badge> badges = new ArrayList<>(badgesManager.findAll());
        List<PublicBadgeTO> result = new LinkedList<>();
        for (Badge badge : badges) {
            result.add(badgesTOService.buildPublicBadgeTO(badge));
        }
        return result;
    }

    /**
     * Retrieves representation of an Application resource
     *
     * @param badgeId
     * @return an instance of PublicApplicationTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{badgeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PublicBadgeTO getResource(@PathParam("badgeId") long badgeId) throws EntityNotFoundException {
        Badge badge = badgesManager.findById(badgeId);
        PublicBadgeTO badgeTO = badgesTOService.buildPublicBadgeTO(badge);
        return badgeTO; 
    }

    /**
     * Updates an Application resource
     *
     * @param updatedBadgeTO
     * @param badgeId
     * @return an instance of PublicApplicationTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{badgeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Resource(PublicBadgeTO updatedBadgeTO, @PathParam("badgeId") long badgeId) throws EntityNotFoundException {
        Badge badgeToUpdate = badgesManager.findById(badgeId);
        badgesTOService.updateBadgeEntity(badgeToUpdate, updatedBadgeTO);
        badgesManager.update(badgeToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an Application resource
     *
     * @param badgeId
     * @return an instance of PublicApplicationTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{badgeId}")
    public Response deleteResource(@PathParam("badgeId") long badgeId) throws EntityNotFoundException {
        Badge badgeToDelete = badgesManager.findById(badgeId);
        badgesManager.delete(badgeId);
        return Response.ok().build();
    }

}
