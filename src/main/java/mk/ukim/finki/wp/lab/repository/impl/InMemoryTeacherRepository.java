//package mk.ukim.finki.wp.lab.repository.impl;
//
////import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Teacher;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class InMemoryTeacherRepository {
//    public List<Teacher> findAll() {
//        return DataHolder.teachers;
//    }
//
//    public Teacher getByID(Long ID) {
//        List<Teacher> teachersList = DataHolder.teachers;
//        for (Teacher teacher : teachersList) {
//            if (teacher.getId() == ID)
//                return teacher;
//        }
//        return null;
//    }
//}
