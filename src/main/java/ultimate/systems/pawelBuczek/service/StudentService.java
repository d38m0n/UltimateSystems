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
import ultimate.systems.pawelBuczek.repository.StudentRepository;
import ultimate.systems.pawelBuczek.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private ModelMapper modelMapper;


    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.modelMapper = new ModelMapper();
        this.teacherRepository = teacherRepository;
    }

    public Student addTeacherToList(Long idStudent, Long idTeacher) {
        return studentRepository.save(getStudentById(idStudent)
                .addTeacher(getTeacherById(idTeacher)));
    }

    public Student createNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deletedTeacherWithList(Long idStudent, Long idTeacher) {
        return studentRepository.save(getStudentById(idStudent)
                .deleteTeacher(getTeacherById(idTeacher)));
    }

    public void deleteStudent(Long idStudent) {
        Student student = getStudentById(idStudent);
        if (student.getTeachers().isEmpty()) {
            studentRepository.deleteById(idStudent);
        } else {
            throw new CanNotDeleteException();
        }
    }

    public Student editStudent(Student source) {
        return studentRepository.save(getStudentById(source.getId())
                .update(source));
    }

    public List<Student> findAllBySortedTeacher(Pageable pageable) {
        return studentRepository.findAll(pageable).getContent();
    }

    public Student getStudentById(Long idStudent) {
        return studentRepository.findById(idStudent)
                .orElseThrow(StudentNotFoundException::new);
    }

    public List<StudentReadModel> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(student -> modelMapper.map(student, StudentReadModel.class))
                .collect(Collectors.toList());
    }

    private Teacher getTeacherById(Long idTeacher) {
        return teacherRepository
                .findById(idTeacher)
                .orElseThrow(TeacherNotFoundException::new);
    }

    public Student getTeacherByName(String name) {
        return studentRepository.findAll()
                .stream().filter(student -> student.getName().equals(name))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student getTeacherBySurname(String surname) {
        return studentRepository.findAll()
                .stream().filter(student -> student.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);
    }

    public List<StudentReadModel> findAllStudentsWithTeacherId(Long idTeacher) {
        return studentRepository.findAllByIdTeacher(idTeacher)
                .stream().map(student -> modelMapper.map(student, StudentReadModel.class))
                .collect(Collectors.toList());

    }
}
