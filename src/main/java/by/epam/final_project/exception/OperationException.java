package by.epam.final_project.exception;

public class OperationException extends Exception {

    private static final long serialVersionUID = 2880920176828117512L;

    public OperationException() {
        super();
    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }

}
