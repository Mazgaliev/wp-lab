package mk.ukim.finki.wp.lab.model.exceptions;

public class NoCourseSelectedException extends RuntimeException {
    public NoCourseSelectedException() {
        super("No course selected");
    }
}
