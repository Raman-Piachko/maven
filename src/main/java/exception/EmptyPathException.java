package exception;

public class EmptyPathException extends RuntimeException {
    public EmptyPathException(String message) {
        super(message);
    }
}