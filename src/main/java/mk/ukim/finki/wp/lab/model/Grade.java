package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Character grade;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private LocalDateTime timestamp;

    public Grade(Character grade, Student student, Course course, LocalDateTime timestamp) {
        this.grade = grade;
        this.student = student;
        this.course = course;
        this.timestamp = timestamp;
    }

    public Grade() {

    }
}
