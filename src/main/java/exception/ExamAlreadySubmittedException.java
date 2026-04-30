package exception;

public class ExamAlreadySubmittedException extends RuntimeException {
    public ExamAlreadySubmittedException() {
        super();
    }

    public ExamAlreadySubmittedException(String message) {
        super(message);
    }

    public ExamAlreadySubmittedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamAlreadySubmittedException(Throwable cause) {
        super(cause);
    }
}

