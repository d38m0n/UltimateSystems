package ultimate.systems.pawelBuczek.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ultimate.systems.pawelBuczek.entity.Student;
import ultimate.systems.pawelBuczek.entity.Teacher;
import ultimate.systems.pawelBuczek.exception.CanNotDeleteException;
import ultimate.systems.pawelBuczek.exception.StudentNotFoundException;
import ultimate.systems.pawelBuczek.exception.TeacherNotFoundException;
import ultimate.systems.pawelBuczek.model.StudentReadModel;
import ultimate.systems.pawelBuczek.model.TeacherReadModel;
import ultimate.systems.pawelBuczek.repository.StudentRepository;
import ultimate.systems.pawelBuczek.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;


    public TeacherService(TeacherRepository teacherRepository,
                          StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = new ModelMapper();
        this.studentRepository = studentRepository;
    }

    public Teacher addStudentToList(Long idStudent, Long idTeacher) {
        return teacherRepository.save(getTeacherById(idTeacher)
                .addStudent(getStudent(idStudent)));
    }

    public Teacher createNewTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher deleteStudentToList(Long idStudent, Long idTeacher) {
        return teacherRepository.save(getTeacherById(idTeacher)
                .deleteStudent(getStudent(idStudent))
        );
    }

    public void deleteTeacher(Long idTeacher) {

        Teacher teacher = getTeacherById(idTeacher);
        if (teacher.getStudents().isEmpty()) {
            teacherRepository.deleteById(idTeacher);
        } else {
            throw new CanNotDeleteException();
        }
    }


    public Teacher editStudent(Teacher source) {
        return teacherRepository.save(getTeacherById(source.getId())
                .update(source));
    }

    public List<Teacher> findAllBySortedTeacher(Pageable pageable) {
        return teacherRepository
                .findAll(pageable)
                .getContent();
    }

    public List<TeacherReadModel> getAllTeachersModel() {
        return teacherRepository.findAll().stream()
                .map(teacher -> modelMapper.map(teacher, TeacherReadModel.class))
                .collect(Collectors.toList());
    }

    public Teacher getTeacherById(Long idTeacher) {
        return teacherRepository.findById(idTeacher)
                .orElseThrow(TeacherNotFoundException::new);
    }

    private Student getStudent(Long idStudent) {
        return studentRepository
                .findById(idStudent)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Teacher getTeacherByName(String name) {
        return teacherRepository.findAll()
                .stream().filter(teacher -> teacher.getName().equals(name))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);

    }

    public Teacher getTeacherBySurname(String surname) {
        return teacherRepository.findAll()
                .stream().filter(teacher -> teacher.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);
    }

    public List<TeacherReadModel> findAllTeachersWithStudentId(Long idStudent) {

        return teacherRepository.findAllByIdStudent(idStudent)
                .stream().map(teacher -> modelMapper.map(teacher, TeacherReadModel.class))
                .collect(Collectors.toList());
    }
}
