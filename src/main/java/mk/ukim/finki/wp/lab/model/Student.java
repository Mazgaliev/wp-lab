package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Students")
public class Student {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @ManyToMany(mappedBy = "students")
    public List<Course> courses;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "username=" + username +
//                ", password=" + password +
//                ", name=" + name +
//                ", surname=" + surname + "}";
//    }


    public Student() {

    }
}
