package mk.ukim.finki.wp.lab.service.serviceImplementation;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyGradedException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentNotEnrolledException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Grade save(String username, Long courseId, Character grade, LocalDateTime dateTime) {
        Course c = courseRepository.findCourseByCourseId(courseId);
        Student s = studentRepository.findStudentByUsername(username);

        Grade g = new Grade(grade, s, c, dateTime);
        if (!c.getStudents().contains(s)) {
            throw new StudentNotEnrolledException();
        }
        if (g.getCourse().getStudents().contains(s)) {
            throw new StudentAlreadyGradedException();
        }
        gradeRepository.save(g);
        return g;
    }

    @Override
    public Grade getStudentCourseGrade(Student s, Course c) {
        return gradeRepository.getGradeByStudentAndCourse(s, c);
    }

    @Override
    public List<Grade> getGradesByCourse(Course course) {
        return gradeRepository.getGradesByCourse(course);
    }

    @Override
    public List<Grade> getGradesForCourseAfterTimeStamp(Course course, LocalDateTime dateTime) {
        return gradeRepository.getGradesByTimestampAfterAndCourse(dateTime, course);
    }

    @Override
    public List<Grade> allGrades() {
        return gradeRepository.findAll();
    }
}
