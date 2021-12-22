package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    public List<Teacher> listAll();

    public Teacher getTeacherById(Long ID);

    public void addTeacher(String name, String surname);

    public void deleteTeacher(Long id);
}
