package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.AuthentificationFailedException;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.services.crud.ApplicationsManagerLocal;
import ch.heigvd.gamification.services.to.ApplicationsTOServiceLocal;
import ch.heigvd.gamification.to.PrivateApplicationTO;
import ch.heigvd.gamification.to.PublicApplicationTO;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Khaled Basbous
 */
@Stateless
@Path("applications")
public class ApplicationResource {

    @Context
    private UriInfo context;

    @EJB
    ApplicationsManagerLocal applicationsManager;

    @EJB
    ApplicationsTOServiceLocal applicationsTOService;

    /**
     * Creates a new instance of ApplicationsResource
     */
    public ApplicationResource() {
    }

    /**
     * Creates a new Application resource from the provided representation
     *
     * @param newApplicationTO
     * @return an instance of PublicApplicationTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PrivateApplicationTO newApplicationTO) {
        Application newApplication = new Application();
        applicationsTOService.updateApplicationEntity(newApplication, newApplicationTO);
        String newApplicationId = applicationsManager.create(newApplication);
        URI createdURI = context.getAbsolutePathBuilder().path(newApplicationId).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Application resources
     *
     * @return a list of PublicApplicationTO instances
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PublicApplicationTO> getResourceList() {
        List<Application> applications = applicationsManager.findAll();
        List<PublicApplicationTO> result = new LinkedList<>();
        for (Application application : applications) {
            result.add(applicationsTOService.buildPublicApplicationTO(application));
            System.out.println("Taille "+application.getPlayers().size()+"\n");
        }
        return result;
    }

    /**
     * Retrieves representation of an Application resource
     *
     * @param apiKey
     * @return an instance of PublicApplicationTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public PublicApplicationTO getResource(@PathParam("apiKey") String apiKey) throws EntityNotFoundException {
        Application application = applicationsManager.findById(apiKey);
        PublicApplicationTO applicationTO = applicationsTOService.buildPublicApplicationTO(application);
        return applicationTO;
    }

    /**
     * Updates an Application resource
     *
     * @param updatedApplicationTO
     * @param apiKey
     * @param apiSecret
     * @return an instance of PublicApplicationTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     * @throws ch.heigvd.gamification.exceptions.AuthentificationFailedException
     */
    @PUT
    @Path("{apiKey}/{apiSecret}")
    @Consumes({"application/json"})
    public Response Resource(PrivateApplicationTO updatedApplicationTO, @PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret) throws EntityNotFoundException, AuthentificationFailedException {
        Application applicationToUpdate = applicationsManager.checkApiSecret(apiKey, apiSecret);
        applicationsTOService.updateApplicationEntity(applicationToUpdate, updatedApplicationTO);
        applicationsManager.update(applicationToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an Application resource
     *
     * @param apiKey
     * @param apiSecret
     * @return an instance of PublicApplicationTO
     * @throws ch.heigvd.gamification.exceptions.EntityNotFoundException
     * @throws ch.heigvd.gamification.exceptions.AuthentificationFailedException
     */
    @DELETE
    @Path("{apiKey}/{apiSecret}")
    public Response deleteResource(@PathParam("apiKey") String apiKey, @PathParam("apiSecret") String apiSecret) throws EntityNotFoundException, AuthentificationFailedException {
        Application applicationToDelete = applicationsManager.checkApiSecret(apiKey, apiSecret);
        applicationsManager.delete(apiKey);
        return Response.ok().build();
    }

}
