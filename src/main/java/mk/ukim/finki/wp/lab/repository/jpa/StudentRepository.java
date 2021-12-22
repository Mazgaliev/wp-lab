package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findAll();


    List<Student> findStudentsByNameOrSurname(String name, String surname);

    Student findStudentByUsername(String username);

    Student save(Student s);



    void delete(Student s);


}
