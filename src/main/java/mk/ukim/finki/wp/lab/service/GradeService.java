package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface GradeService {

    Grade save(String username, Long courseId, Character grade, LocalDateTime dateTime);

    Grade getStudentCourseGrade(Student s, Course c);

    List<Grade> getGradesByCourse(Course course);

    List<Grade> getGradesForCourseAfterTimeStamp(Course course, LocalDateTime dateTime);

    List<Grade> allGrades();
}
