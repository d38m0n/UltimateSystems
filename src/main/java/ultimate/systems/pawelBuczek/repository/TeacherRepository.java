package ultimate.systems.pawelBuczek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ultimate.systems.pawelBuczek.entity.Student;
import ultimate.systems.pawelBuczek.entity.Teacher;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    Teacher save(Teacher student);

    Page<Teacher> findAll(Pageable p);

    List<Teacher> findAll();

    Optional<Teacher> findById(Long id);

    Optional<Teacher> findByName(String name);

    Optional<Teacher> findBySurname(String surname);

    void deleteById(Long idTeacher);
    @Query(value = "select * from teachers  t where t.student_id = :id",
            nativeQuery = true)
    List<Teacher> findAllByIdStudent(@Param("id") Long id);

}
