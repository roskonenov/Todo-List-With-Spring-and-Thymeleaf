package personal.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
