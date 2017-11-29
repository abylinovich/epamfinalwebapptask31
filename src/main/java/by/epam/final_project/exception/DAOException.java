package by.epam.final_project.exception;

public class DAOException extends OperationException {

    private static final long serialVersionUID = 8971270653220319616L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

}
