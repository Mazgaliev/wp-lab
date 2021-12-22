package mk.ukim.finki.wp.lab.service.serviceImplementation;

import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.repository.impl.InMemoryCourseRepository;

import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
//import mk.ukim.finki.wp.lab.repository.impl.InMemoryStudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {

        return studentRepository.findStudentsByNameOrSurname(text, text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        Student s = new Student(username, password, name, surname);
        //studentRepository.findAllStudents().add(s);
        studentRepository.save(s);
        return s;
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }

    @Override
    public void delete(Student s) {
        //studentRepository.findAllStudents().removeIf(a -> a.equals(s));
        studentRepository.delete(s);
    }

}
