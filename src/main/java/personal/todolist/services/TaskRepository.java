package personal.todolist.services;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.todolist.models.Task;

interface TaskRepository extends JpaRepository<Task, Long> {
}
