package ultimate.systems.pawelBuczek.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Not found this Student");
    }
}
