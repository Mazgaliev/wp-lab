package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyGradedException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentNotEnrolledException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final GradeService gradeService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, GradeService gradeService, CourseService courseService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
        this.courseService = courseService;
    }

//    @GetMapping
//    public String getStudentsPage(@RequestParam(required = false) String error, Model model) {
//
//
//    }

    @GetMapping("/Add")
    public String viewPage() {

        return "createStudent";
    }

    @GetMapping("/Add-Grade")
    public String viewPage1(Model model) {
        List<Course> courseList = courseService.listCourses();
        model.addAttribute("courses", courseList);

        return "AddGradeToSTudent";
    }

    @PostMapping("/Added")
    public String addGrade(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime,
                           @RequestParam String username,
                           @RequestParam String courseId,
                           @RequestParam Character grade) {


        String u = username;
        Long cid = Long.parseLong(courseId);
        Character g = grade;
        try {
            gradeService.save(u, cid, g, datetime);
            return "redirect:/course";
        } catch (StudentNotEnrolledException | StudentAlreadyGradedException e) {
            return "redirect:/course";
        }
    }

    @PostMapping("/create")
    public String createStudent(@RequestParam String username, @RequestParam String password,
                                @RequestParam String name, @RequestParam String surname) {
        studentService.save(username, password, name, surname);
        return "redirect:/course";
    }


}
