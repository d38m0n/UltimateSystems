package ultimate.systems.pawelBuczek.exception;

public class CanNotDeleteException extends RuntimeException {
    public CanNotDeleteException() {
        super("Foreign key exists");
    }
}
