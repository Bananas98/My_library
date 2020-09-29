package exception;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String messageKey) {
        super(messageKey);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

}