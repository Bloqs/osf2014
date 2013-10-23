package ch.heigvd.gamification.services.crud;

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

    long create(Application applicationData);

    void update(Application newState) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    Application findById(long id) throws EntityNotFoundException;

    List<Application> findAll();

}
