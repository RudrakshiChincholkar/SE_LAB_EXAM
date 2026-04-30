package exception;

public class ResponsePersistenceFailedException extends RuntimeException {
    public ResponsePersistenceFailedException() {
        super();
    }

    public ResponsePersistenceFailedException(String message) {
        super(message);
    }

    public ResponsePersistenceFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponsePersistenceFailedException(Throwable cause) {
        super(cause);
    }
}

