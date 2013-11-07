package ch.heigvd.gamification.rest;

import ch.heigvd.gamification.exceptions.AuthentificationFailedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Olivier Liechti
 */
@Provider
public class AuthentificationExceptionMapper implements ExceptionMapper<AuthentificationFailedException> {

    @Override
    public Response toResponse(AuthentificationFailedException exception) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
