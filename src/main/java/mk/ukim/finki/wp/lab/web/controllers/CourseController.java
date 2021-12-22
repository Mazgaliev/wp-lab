package mk.ukim.finki.wp.lab.web.controllers;


import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidCourseIdException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyInCourseException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GradeService gradeService;
    Integer pagenumber = 0;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getHomePage(
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String error, Model model) {

        Page<Course> courseList = courseService.getAllCourses(pagenumber, pageSize);

        model.addAttribute("courses", courseList);

        return "listCourses";
    }

    @GetMapping("/next")
    public String nextPage() {

        pagenumber += 1;


        return "redirect:/course";
    }

    @GetMapping("/back")
    public String goBack() {
        pagenumber -= 1;

        return "redirect:/course";
    }

    @GetMapping("/add")
    public String displayPage(Model model) {
        model.addAttribute("profs", teacherService.listAll());
        model.addAttribute("courseTypes", new String[]{"WINTER", "SUMMER", "MANDATORY", "ELECTIVE"});
        return "CreateCourse";
    }

    @PostMapping("/create")
    public String saveCourse(@RequestParam String courseName,
                             @RequestParam String courseDesc,
                             @RequestParam Long TeacherID, @RequestParam String courseType
            , Model model) {
        Teacher teacher = null;
        try {
            teacher = teacherService.getTeacherById(TeacherID);
            Type type = Type.valueOf(Type.class, courseType);
            Course course = new Course(courseName, courseDesc, new ArrayList<>(), teacher, type);
//            courseService.listCourses().removeIf(c -> c.getName().equals(course.getName()));
//            courseService.listCourses().add(course);
            courseService.createCourse(course);
        } catch (InvalidCourseIdException exception) {

            model.addAttribute("hasError", true);
            model.addAttribute("message", exception.getMessage());

            return "redirect:/course/edit/{id}";
        }

        return "redirect:/course";
    }

    @PostMapping("/StudentEnrollmentSummary")
    public String enrollStudent(@RequestParam String std, Model model) {
        String val[] = std.split(" ");
        String username = val[0];
        String cId = val[1];
        Long kurs = Long.parseLong(cId);
        Course c = courseService.getCourseByID(kurs);
        Student s = studentService.findByUsername(username);

        try {
            courseService.addStudentInCourse(username, kurs);
            model.addAttribute("course", c.getName());
            model.addAttribute("grades", gradeService.getGradesByCourse(c));
            return "studentsInCourse";
        } catch (StudentAlreadyInCourseException e) {
            model.addAttribute("course", c.getName());
            model.addAttribute("grades", gradeService.getGradesByCourse(c));
            return "studentsInCourse";
        }

    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course c = courseService.getCourseByID(id);
        List<Teacher> t = teacherService.listAll();
        if (c == null) {
            return "redirect:/course?error=" + String.format("InvalidCourseId");
        }
        // model.addAttribute("")
        return "CreateCourse";
    }

    @GetMapping("/listStudents")
    public String listStudent(@RequestParam Long kurs, Model model) {
        Course c = courseService.getCourseByID(kurs);
        List<Student> students = studentService.listAll();


        model.addAttribute("students", studentService.listAll());
        model.addAttribute("courseID", kurs);

        return "listStudents";
    }

    @GetMapping("/grades")
    public String showStudentGrades(Model model) {
        model.addAttribute("gradesStudents", gradeService.allGrades());
        model.addAttribute("courses", courseService.listCourses());
        return "listStudentsByTime";
    }

    @PostMapping("/grades")
    public String showStudentGradesAfterTime(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                             @RequestParam(required = false) Long course, Model model) {

        List<Grade> grades = gradeService.getGradesForCourseAfterTimeStamp(courseService.getCourseByID(course), date);
        model.addAttribute("gradesStudents", grades);
        model.addAttribute("courses", courseService.listCourses());
        return "listStudentsByTime";
    }

//    @PostMapping("/Enroll/{id}")
//    public String enroll(@PathVariable Long id, Model model) {
//        Course c = courseService.getCourseByID(id);
//        List<Student> students = c.getStudents();
//
//
//
//    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {

        //courseService.listCourses().removeIf(course -> course.getCourseId().equals(id));
        courseService.deleteCourse(id);
        return "redirect:/course";
    }
}
