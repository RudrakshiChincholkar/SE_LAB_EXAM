package exception;

public class QuestionsNotAvailableForExamException extends RuntimeException {
    public QuestionsNotAvailableForExamException() {
        super();
    }

    public QuestionsNotAvailableForExamException(String message) {
        super(message);
    }

    public QuestionsNotAvailableForExamException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionsNotAvailableForExamException(Throwable cause) {
        super(cause);
    }
}

