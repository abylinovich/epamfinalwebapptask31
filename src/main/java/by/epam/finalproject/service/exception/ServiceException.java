package by.epam.finalproject.service.exception;

import by.epam.finalproject.exception.OperationException;

public class ServiceException extends OperationException {

    private static final long serialVersionUID = -2225226742257160800L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
