package personal.todolist.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import personal.todolist.models.Task;
import personal.todolist.repositories.TaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() {
        List<Task> tasks = List.of(new Task(1L, "Test", false), new Task(2L, "Test", true));
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        verify(taskRepository).findAll();
        assertEquals(2, result.size());
        assertEquals(1L, result.getFirst().getId());
        assertEquals("Test", result.getLast().getName());
        assertTrue(result.getLast().isCompleted());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoTasks() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        List<Task> emptyList = taskService.getAllTasks();

        verify(taskRepository).findAll();
        assertEquals(0, emptyList.size());
    }

    @Test
    void shouldCreateNewTask() {
        Task task = new Task(1L, "Test", false);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task saved = taskService.createTask("Test");

        verify(taskRepository).save(any(Task.class));
        assertEquals(1L, saved.getId());
        assertEquals("Test", saved.getName());
        assertFalse(saved.isCompleted());
    }

    @Test
    void shouldDeleteTaskById() {
        Long id = 5L;
        doNothing().when(taskRepository).deleteById(id);

        taskService.deleteTask(id);

        verify(taskRepository).deleteById(id);
    }

    @Test
    void shouldToggleCompletionIfTaskExist() {
        Task task = new Task(1L, "Test", false);
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        taskService.toggleTask(task.getId());

        verify(taskRepository).findById(task.getId());
        verify(taskRepository).save(task);
        assertTrue(task.isCompleted());
    }
}