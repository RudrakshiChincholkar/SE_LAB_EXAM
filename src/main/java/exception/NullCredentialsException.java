package exception;

public class NullCredentialsException extends RuntimeException {
    public NullCredentialsException() {
        super();
    }

    public NullCredentialsException(String message) {
        super(message);
    }

    public NullCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullCredentialsException(Throwable cause) {
        super(cause);
    }
}

