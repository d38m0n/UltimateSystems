package ultimate.systems.pawelBuczek.entity;

import org.modelmapper.ModelMapper;
import ultimate.systems.pawelBuczek.model.StudentReadModel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "teachers")
public class Teacher extends BaseData {

    private String subject;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Set<Student> students;

    public Teacher addStudent(Student source) {
        this.students.add(source);
        return this;
    }

    public Teacher deleteStudent(Student student) {
        this.students.remove(student);
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public List<StudentReadModel> getStudents() {
        ModelMapper modelMapper = new ModelMapper();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentReadModel.class))
                .collect(Collectors.toList());
    }

    public Teacher update(final Teacher source) {
        super.setName(source.getName());
        super.setSurname(source.getSurname());
        super.setAge(source.getAge());
        super.setEmail(source.getEmail());
        this.subject = source.subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
