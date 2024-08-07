package koff.expense.exception;

public class NoAuthorityException extends RuntimeException {

    public NoAuthorityException(String message) {
        super(message);
    }

    public NoAuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthorityException(Throwable cause) {
        super(cause);
    }
}
