package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentAlreadyExists extends RuntimeException {
    public StudentAlreadyExists() {
        super("Student already exists");
    }
}
