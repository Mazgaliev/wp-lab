package mk.ukim.finki.wp.lab.model.exceptions;


public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("Student username already exists");
    }
}
