package personal.todolist.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import personal.todolist.models.Task;
import personal.todolist.services.TaskService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void shouldReturnTasksViewWithTasksInModel() throws Exception {
        List<Task> tasks = List.of(new Task(1L, "Test", false));
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attribute("tasks", hasSize(1)))
                .andExpect(model().attribute("tasks", contains(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("Test")),
                                hasProperty("completed", is(false))
                        )
                )));
    }

    @Test
    void shouldCreateTaskAndRedirect() throws Exception {
        mockMvc.perform(post("/")
                .param("title", "New Task"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(taskService, times(1)).createTask("New Task");
    }

    @Test
    void shouldDeleteTaskAndRedirect() throws Exception {
        mockMvc.perform(get("/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(taskService, times(1)).deleteTask(1L);
    }

    @Test
    void shouldToggleTaskAndRedirect() throws Exception {
        mockMvc.perform(get("/1/toggle"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(taskService, times(1)).toggleTask(1L);
    }
}