package exception;

public class LoginRequiredException extends RuntimeException {
    public LoginRequiredException() {
        super();
    }

    public LoginRequiredException(String message) {
        super(message);
    }

    public LoginRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginRequiredException(Throwable cause) {
        super(cause);
    }
}

