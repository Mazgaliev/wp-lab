package mk.ukim.finki.wp.lab.web.filters;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter
public class CourseIdFilter implements Filter {
    private final CourseService courseService;

    public CourseIdFilter(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String kurs = request.getParameter("kurs");
        Course c = null;
        if (kurs != null) {
            Long k = Long.parseLong(kurs);
            c = courseService.listCourses().stream().filter(r -> r.getCourseId().equals(k)).findFirst().get();
        }
        String path = request.getServletPath();

        if ("/course/add".equals(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        if (c != null && "/course/listSTudents".equals(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        if (c == null && !"/course".equals(path)) {
            response.sendRedirect("/course");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
