package personal.todolist.services;

import org.springframework.stereotype.Service;
import personal.todolist.models.Task;
import personal.todolist.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(String title) {
        return taskRepository.save(
                new Task().setName(title).setCompleted(false)
        );
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        taskRepository.save(task.setCompleted(!task.isCompleted()));
    }
}
