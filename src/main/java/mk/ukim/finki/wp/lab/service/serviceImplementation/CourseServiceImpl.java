package mk.ukim.finki.wp.lab.service.serviceImplementation;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidCourseIdException;
//import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyInCourseException;
//import mk.ukim.finki.wp.lab.repository.impl.InMemoryCourseRepository;

//import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
//import mk.ukim.finki.wp.lab.repository.impl.InMemoryStudentRepository;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyInCourseException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;

        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        List<Student> lista = courseRepository.findAllByCourseId(courseId);

        return lista;
    }

    @Override
    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.delete(courseRepository.findCourseByCourseId(id));
    }

    @Override
    public Page<Course> getAllCourses(Integer pageNo, Integer pageSize) {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Course> pagedResult = courseRepository.findAll(pageable);
        return pagedResult;
    }

    @Override
    public Course createCourse(Course c) {
        return courseRepository.save(c);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        Student student = studentRepository.findStudentByUsername(username);

        List<Student> students = course.getStudents();
        if (students.contains(student)) {
            throw new StudentAlreadyInCourseException();
        }
        students.add(student);
        course.setStudents(students);

        return courseRepository.save(course);
    }

    @Override
    public Course getCourseByID(Long id) {
        if (courseRepository.findById(id) == null) {
            throw new InvalidCourseIdException();
        }
        return courseRepository.findCourseByCourseId(id);
    }
}
