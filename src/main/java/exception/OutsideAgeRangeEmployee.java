package exception;

public class OutsideAgeRangeEmployee extends RuntimeException {
    public OutsideAgeRangeEmployee(String message) {
        super(message);
    }
}
