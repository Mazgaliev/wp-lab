package mk.ukim.finki.wp.lab.model.exceptions;

public class NameOrSurnameDoesNotExistException extends RuntimeException {
    public NameOrSurnameDoesNotExistException() {
        super("Name or Surname do not exist");
    }
}
