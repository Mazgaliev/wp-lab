//package mk.ukim.finki.wp.lab.bootstrap;
//
//import lombok.Getter;
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.model.Teacher;
//import mk.ukim.finki.wp.lab.model.Type;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Getter
//public class DataHolder {
//    public static List<Student> students = new ArrayList<>();
//    public static List<Course> courses = new ArrayList<>();
//    public static List<Teacher> teachers = new ArrayList<>();
//
//    @PostConstruct
//    public void init() {
//        students.add(new Student("Mazgaliev1", "maz", "Mite", "Mazgaliev"));
//        students.add(new Student("Dinev1", "din", "Martin", "Dinev"));
//        students.add(new Student("Janev1", "jan", "Ivan", "Janev"));
//        students.add(new Student("Drobarov1", "drob", "Martin", "Drobarov"));
//        students.add(new Student("Simeonov1", "sim", "Krste", "Simeonov"));
//
//        teachers.add(new Teacher("Biljana", "Tojtovska"));
//        teachers.add(new Teacher("Marija", "Mihova"));
//        teachers.add(new Teacher("Vesna", "Dimitrova"));
//        teachers.add(new Teacher("Riste", "Stojanov"));
//        teachers.add(new Teacher("Vladimir", "Zdravevski"));
//
//        Student s = students.get(0);
//        courses.add(new Course("Calculus", "You learn to use some math", new ArrayList<>(), teachers.get(0), Type.ELECTIVE));
//        courses.add(new Course("DiscreteMath1", "You learn to prove some math", new ArrayList<Student>(), teachers.get(1), Type.MANDATORY));
//        courses.add(new Course("Programming", "You learn to program some math", new ArrayList<Student>(), teachers.get(2), Type.WINTER));
//        courses.add(new Course("Intro to CS", "You learn how computers use math", new ArrayList<Student>(), teachers.get(3), Type.WINTER));
//        courses.add(new Course("Professional skills", "You learn to make a CV", new ArrayList<Student>(), teachers.get(4), Type.SUMMER));
//
//
//    }
//}
