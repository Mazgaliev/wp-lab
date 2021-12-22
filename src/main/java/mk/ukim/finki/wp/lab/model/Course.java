package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;

    private Type type;

    private String name;

    private String description;

    @ManyToMany
    public List<Student> students;

    @ManyToOne
    public Teacher teacher;


    public Course(String name, String description, List<Student> students, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
        this.type = type;
    }


    public Course() {

    }

}

