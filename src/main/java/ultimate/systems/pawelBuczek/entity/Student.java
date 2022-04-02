package ultimate.systems.pawelBuczek.entity;

import org.modelmapper.ModelMapper;
import ultimate.systems.pawelBuczek.model.TeacherReadModel;

import javax.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "students")
public class Student extends BaseData {

    private String curse;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Set<Teacher> teachers;


    public String getCurse() {
        return curse;
    }

    public void setCurse(String curse) {
        this.curse = curse;
    }

    public List<TeacherReadModel> getTeachers() {
        ModelMapper modelMapper = new ModelMapper();
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherReadModel.class))
                .collect(Collectors.toList());
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Student addTeacher(Teacher source) {
        this.teachers.add(source);
        return this;
    }

    public Student deleteTeacher(Teacher source) {
        this.teachers.remove(source);
        return this;
    }

    public Student update(final Student source) {
        super.setName(source.getName());
        super.setSurname(source.getSurname());
        super.setAge(source.getAge());
        super.setEmail(source.getEmail());
        this.curse = source.curse;
        return this;
    }

}

