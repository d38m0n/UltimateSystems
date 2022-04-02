package ultimate.systems.pawelBuczek.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ultimate.systems.pawelBuczek.entity.Teacher;
import ultimate.systems.pawelBuczek.repository.TeacherRepository;

@Repository
public interface SqlTeacherRepository extends TeacherRepository, JpaRepository<Teacher, Long> {
}
