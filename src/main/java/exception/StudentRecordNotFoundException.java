package exception;

public class StudentRecordNotFoundException extends RuntimeException {
    public StudentRecordNotFoundException() {
        super();
    }

    public StudentRecordNotFoundException(String message) {
        super(message);
    }

    public StudentRecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentRecordNotFoundException(Throwable cause) {
        super(cause);
    }
}

