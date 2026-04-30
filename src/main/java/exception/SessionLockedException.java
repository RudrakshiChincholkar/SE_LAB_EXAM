package exception;

public class SessionLockedException extends RuntimeException {
    public SessionLockedException() {
        super();
    }

    public SessionLockedException(String message) {
        super(message);
    }

    public SessionLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionLockedException(Throwable cause) {
        super(cause);
    }
}

