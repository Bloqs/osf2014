package ch.heigvd.gamification.services.crud;

import ch.heigvd.gamification.exceptions.AuthentificationFailedException;
import ch.heigvd.gamification.exceptions.EntityNotFoundException;
import ch.heigvd.gamification.model.Application;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Khaled Basbous
 */
@Local
public interface ApplicationsManagerLocal {

    String create(Application applicationData);

    void update(Application newState) throws EntityNotFoundException;

    void delete(String apiKey) throws EntityNotFoundException;

    Application findById(String apiKey) throws EntityNotFoundException;

    List<Application> findAll();
    
    Application checkApiSecret(String apiKey, String secret) throws AuthentificationFailedException, EntityNotFoundException;

}
