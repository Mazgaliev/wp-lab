package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyInCourseException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Enrollment-summary", urlPatterns = "/StudentEnrollmentSummaryy")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;


    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    @Transactional
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String values = req.getParameter("std");
        String[] split = values.split(" ");
        String student = split[0];
        String course = split[1];
        Long kurs = Long.parseLong(course);

        Course c = courseService.getCourseByID(kurs);
        try {

            List<Student> students = c.getStudents();
            students.add(studentService.findByUsername(student));
            c.setStudents(students);
            context.setVariable("course", c.getName());
            context.setVariable("students", c.getStudents());
            req.getSession().setAttribute("kurs", c);
            this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
        } catch (StudentAlreadyInCourseException exc) {
            context.setVariable("hasError", true);
            context.setVariable("message", exc.getMessage());
            context.setVariable("students", courseService.getCourseByID(kurs).getStudents());
            context.setVariable("courseID", courseService.getCourseByID(kurs));

            this.springTemplateEngine.process("listStudents.html", context, resp.getWriter());
            // resp.sendRedirect("/listCourses");
        }

    }
}
