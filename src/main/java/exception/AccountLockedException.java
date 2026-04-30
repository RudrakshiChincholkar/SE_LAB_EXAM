package exception;

public class AccountLockedException extends RuntimeException {
    public AccountLockedException() {
        super();
    }

    public AccountLockedException(String message) {
        super(message);
    }

    public AccountLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountLockedException(Throwable cause) {
        super(cause);
    }
}

