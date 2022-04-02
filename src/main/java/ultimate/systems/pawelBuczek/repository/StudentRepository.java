package ultimate.systems.pawelBuczek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ultimate.systems.pawelBuczek.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    List<Student> findAll();

    @Query(value = "select * from students  s where s.teacher_id = :id",
            nativeQuery = true)
    List<Student> findAllByIdTeacher(@Param("id") Long id);

    Page<Student> findAll(Pageable p);

    Optional<Student> findById(Long id);

    Optional<Student> findByName(String name);


    Optional<Student> findBySurname(String surname);

    void deleteById(Long idStudent);

}
