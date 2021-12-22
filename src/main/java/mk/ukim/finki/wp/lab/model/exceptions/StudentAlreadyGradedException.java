package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentAlreadyGradedException extends RuntimeException {

    public StudentAlreadyGradedException() {
        super("Student is already graded");
    }
}
