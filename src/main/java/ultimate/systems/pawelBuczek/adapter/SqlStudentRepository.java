package ultimate.systems.pawelBuczek.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ultimate.systems.pawelBuczek.entity.Student;
import ultimate.systems.pawelBuczek.repository.StudentRepository;

@Repository
public interface SqlStudentRepository extends StudentRepository, JpaRepository<Student, Long> {
}
