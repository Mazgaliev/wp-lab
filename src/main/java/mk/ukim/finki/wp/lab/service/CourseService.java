package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);


    List<Course> listCourses();

    void deleteCourse(Long id);

    Page<Course> getAllCourses(Integer pageNo,Integer pageSize);

    Course createCourse(Course c);

    Course addStudentInCourse(String username, Long courseId);

    Course getCourseByID(Long id);
}
