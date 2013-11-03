package ch.heigvd.gamification.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Olivier Liechti
 */
@ApplicationException(rollback = true)
public class AuthentificationFailedException extends Exception {
	
	

}
