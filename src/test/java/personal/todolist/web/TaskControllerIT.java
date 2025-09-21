package personal.todolist.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import personal.todolist.models.Task;
import personal.todolist.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void shouldLoadTasksPage() throws Exception {
        taskRepository.save(new Task(null, "Task to show", false));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attribute("tasks", hasSize(1)))
                .andExpect(model().attribute("tasks", contains(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("Task to show")),
                                hasProperty("completed", is(false))
                        )
                )));
    }

    @Test
    void shouldCreateTask() throws Exception {
        mockMvc.perform(post("/")
                .param("title", "Created Task"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(1);
        assertThat(tasks.getFirst().getName()).isEqualTo("Created Task");
    }

    @Test
    void shouldDeleteTask() throws Exception {
        Task task = taskRepository.save(new Task(null, "Task to Delete", false));

        mockMvc.perform(get("/" + task.getId() + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }

    @Test
    void shouldToggleTask() throws Exception {
        Task task = taskRepository.save(new Task(null, "Task to Toggle", false));

        mockMvc.perform(get("/" + task.getId() + "/toggle"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Task toggled = taskRepository.findById(task.getId()).orElseThrow();
        assertTrue(toggled.isCompleted());
    }
}