package exception;

public class InvalidMcqIndexSelectedException extends RuntimeException {
    public InvalidMcqIndexSelectedException() {
        super();
    }

    public InvalidMcqIndexSelectedException(String message) {
        super(message);
    }

    public InvalidMcqIndexSelectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMcqIndexSelectedException(Throwable cause) {
        super(cause);
    }
}

