package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String viewHome() {

        return "homeTeachers";
    }

    @GetMapping("/list")
    public String viewTeachers(Model model) {

        model.addAttribute("teachers", teacherService.listAll());

        return "listTeachers";
    }

    @GetMapping("/create")
    public String createTeacher() {

        return "CreateTeacher";
    }

    @GetMapping("/delete/{id}")
    public String delTeacher(@PathVariable Long id) {
        Teacher t = teacherService.getTeacherById(id);
        //teacherService.findAll().removeIf(r -> r.getName().equals(t.getName()));
        teacherService.deleteTeacher(id);
        return "redirect:/teachers/list";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable Long id, Model model) {
        Teacher t = teacherService.getTeacherById(id);
        model.addAttribute("teacher", t);
        model.addAttribute("teachers", teacherService.listAll());
        return "editTeacher";
    }

    @PostMapping("/edit-teacher")
    public String edit(@RequestParam String teacherName, @RequestParam String teacherSurname, @RequestParam Long TeacherID) {
        Teacher t = new Teacher(teacherName, teacherSurname);
        Teacher t1 = teacherService.getTeacherById(TeacherID);
        teacherService.listAll().removeIf(a -> a.equals(t1));
        teacherService.listAll().add(t);

        return "redirect:/teachers/list";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name,
                         @RequestParam String surname
    ) {
        Teacher t = new Teacher(name, surname);

        //  teacherService.findAll().add(t);
        teacherService.addTeacher(name, surname);
        return "redirect:/teachers/list";
    }
}
