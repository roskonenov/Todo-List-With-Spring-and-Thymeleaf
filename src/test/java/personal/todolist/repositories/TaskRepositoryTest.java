package personal.todolist.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import personal.todolist.models.Task;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldSaveAndFindTask() {
        Task task = new Task(null, "Test", false);

        Task saved = taskRepository.save(task);
        Optional<Task> found = taskRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test");
        assertThat(found.get().isCompleted()).isFalse();
    }

    @Test
    void shouldReturnAllTasks() {
        taskRepository.save(new Task(null, "Task1", false));
        taskRepository.save(new Task(null, "Task2", true));

        List<Task> all = taskRepository.findAll();

        assertThat(all).hasSize(2);
        assertThat(all.getFirst().getName()).isEqualTo("Task1");
        assertThat(all.getLast().getName()).isEqualTo("Task2");
        assertThat(all.getFirst().getId()).isEqualTo(1L);
        assertThat(all.getLast().getId()).isEqualTo(2L);
        assertFalse(all.getFirst().isCompleted());
        assertTrue(all.getLast().isCompleted());
    }

    @Test
    void shouldDeleteTask() {
        Task saved = taskRepository.save(new Task(null, "Test", false));

        taskRepository.deleteById(saved.getId());
        Optional<Task> found = taskRepository.findById(saved.getId());

        assertThat(found).isEmpty();
    }
}