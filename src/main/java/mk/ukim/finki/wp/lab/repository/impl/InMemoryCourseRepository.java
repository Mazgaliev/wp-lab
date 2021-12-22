//package mk.ukim.finki.wp.lab.repository.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class InMemoryCourseRepository {
//    List<Course> findAllCourses() {
//        return DataHolder.courses;
//    }
//
//    public Course findById(Long courseId) {
//
//
//        return DataHolder.courses.stream().filter(kurs -> kurs.getCourseId().equals(courseId)).findAny().get();
//    }
//
//    public List<Student> findAllStudentsByCourse(Long courseId) {
//        for (int i = 0; i < DataHolder.courses.size(); i++) {
//            if (DataHolder.courses.get(i).getCourseId().equals(courseId)) {
//                return DataHolder.courses.get(i).getStudents();
//            }
//
//        }
//        return null;
//    }
//
//    public List<Course> allCourses() {
//        return DataHolder.courses;
//    }
//
//    public Course addStudentToCourse(Student student, Course course) {
//        Course course1 = DataHolder.courses.stream().filter(r -> r.equals(course)).findAny().get();
////        for (int i = 0; i < DataHolder.courses.size(); i++) {
////            if (DataHolder.courses.get(i).getStudents().add(student);
////            }equals(course)) {
//////                DataHolder.courses.get(i).
////        }
//        course1.getStudents().add(student);
//        return course;
//    }
//}
