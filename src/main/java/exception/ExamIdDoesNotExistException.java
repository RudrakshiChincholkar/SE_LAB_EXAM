package exception;

public class ExamIdDoesNotExistException extends RuntimeException {
    public ExamIdDoesNotExistException() {
        super();
    }

    public ExamIdDoesNotExistException(String message) {
        super(message);
    }

    public ExamIdDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamIdDoesNotExistException(Throwable cause) {
        super(cause);
    }
}

