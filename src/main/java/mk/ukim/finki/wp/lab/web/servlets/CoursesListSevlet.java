package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Course-list", urlPatterns = "/servlet/listCourses")
public class CoursesListSevlet extends HttpServlet {
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListSevlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courses", courseService.listCourses());
        this.springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
