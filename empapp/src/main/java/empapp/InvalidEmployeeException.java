package empapp;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class InvalidEmployeeException extends Exception {
}
