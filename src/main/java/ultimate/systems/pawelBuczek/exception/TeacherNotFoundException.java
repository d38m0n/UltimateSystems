package ultimate.systems.pawelBuczek.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException() {
        super("Not found this Teacher");
    }
}
