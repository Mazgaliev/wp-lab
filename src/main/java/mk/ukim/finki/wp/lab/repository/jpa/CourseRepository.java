package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    Page<Course> findAll(Pageable pageable);


    Course findCourseByCourseId(Long courseId);

    List<Student> findAllByCourseId(Long courseId);

    void delete(Course c);

    Course save(Course c);

}
