package exception;

public class InvalidDurationException extends RuntimeException {
    public InvalidDurationException() {
        super();
    }

    public InvalidDurationException(String message) {
        super(message);
    }

    public InvalidDurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDurationException(Throwable cause) {
        super(cause);
    }
}

