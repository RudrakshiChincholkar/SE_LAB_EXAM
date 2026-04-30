package exception;

public class AnswerTextLengthExceedsMaximumLimitException extends RuntimeException {
    public AnswerTextLengthExceedsMaximumLimitException() {
        super();
    }

    public AnswerTextLengthExceedsMaximumLimitException(String message) {
        super(message);
    }

    public AnswerTextLengthExceedsMaximumLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnswerTextLengthExceedsMaximumLimitException(Throwable cause) {
        super(cause);
    }
}

