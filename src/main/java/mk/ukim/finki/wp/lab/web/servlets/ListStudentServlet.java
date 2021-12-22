package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.exceptions.NoCourseSelectedException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "List-Students", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public ListStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        String val = req.getParameter("kurs");
        Course c = null;
        try {
            String val = req.getParameter("kurs");
            c = courseService.getCourseByID(Long.parseLong(val));

            context.setVariable("students", studentService.listAll());
            context.setVariable("courseID", val);
            springTemplateEngine.process("listStudents.html", context, resp.getWriter());
        } catch (NoCourseSelectedException exception) {
            exception.getMessage();
            resp.sendRedirect("/listCourses");
        }

        req.getSession().setAttribute("kurs", c);
//        resp.sendRedirect("/listCourses");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        studentService.save(username, password, name, surname);

        resp.sendRedirect("/AddStudent");
    }
}
