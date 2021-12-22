//package mk.ukim.finki.wp.lab.repository.impl;
//
//import lombok.Getter;
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Student;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//@Getter
//public class InMemoryStudentRepository {
//
//    public List<Student> findAllStudents() {
//        return DataHolder.students;
//    }
//
//    public List<Student> findAllByNameOrSurname(String text) {
//        return DataHolder.students.stream().filter(student -> student.getSurname().equals(text) || student.getName().equals(text)).collect(Collectors.toList());
//    }
//
//    public Student findByUsername(String username) {
//        return DataHolder.students.stream().filter(student -> student.getUsername().equals(username)).findFirst().get();
//    }
//}
