package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String surname;

    private LocalDate dateOfEmployment;

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
        dateOfEmployment = LocalDate.now();
    }

    public Teacher() {

    }
}
