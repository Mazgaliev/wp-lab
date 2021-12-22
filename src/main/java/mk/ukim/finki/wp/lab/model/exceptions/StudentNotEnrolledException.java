package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentNotEnrolledException extends RuntimeException {
    public StudentNotEnrolledException() {
        super("Student is not enrolled");
    }
}
