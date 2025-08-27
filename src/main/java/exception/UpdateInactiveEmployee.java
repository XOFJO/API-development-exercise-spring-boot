package exception;

public class UpdateInactiveEmployee extends RuntimeException {
    public UpdateInactiveEmployee(String message) {
        super(message);
    }
}
