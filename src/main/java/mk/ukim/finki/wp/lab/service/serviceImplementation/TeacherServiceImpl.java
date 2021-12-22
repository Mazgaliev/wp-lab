package mk.ukim.finki.wp.lab.service.serviceImplementation;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.getTeacherById(id);
    }

    @Override
    public void addTeacher(String name, String surname) {


        teacherRepository.save(new Teacher(name, surname));
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher t = teacherRepository.getTeacherById(id);
        teacherRepository.delete(t);
    }


}
