package com.example.core_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /core/tasks creates task")
    void createTask() throws Exception {
        Task request = new Task(null, "New", false);
        Task response = new Task(1L, "New", false);

        given(taskService.create(any(Task.class))).willReturn(response);

        mockMvc.perform(post("/core/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("New")))
                .andExpect(jsonPath("$.completed", is(false)));
    }

    @Test
    @DisplayName("GET /core/tasks returns list")
    void getAllTasks() throws Exception {
        given(taskService.getAll()).willReturn(
                List.of(new Task(1L, "T1", false),
                        new Task(2L, "T2", true))
        );

        mockMvc.perform(get("/core/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("GET /core/tasks/{id} returns task when found")
    void getTaskById_found() throws Exception {
        given(taskService.getById(1L))
                .willReturn(new Task(1L, "T1", false));

        mockMvc.perform(get("/core/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("T1")));
    }

    @Test
    @DisplayName("GET /core/tasks/{id} returns 404 when not found")
    void getTaskById_notFound() throws Exception {
        given(taskService.getById(1L)).willReturn(null);

        mockMvc.perform(get("/core/tasks/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /core/tasks/{id} updates task when found")
    void updateTask_found() throws Exception {
        Task request = new Task(null, "Updated", true);
        Task response = new Task(1L, "Updated", true);

        given(taskService.update(eq(1L), any(Task.class))).willReturn(response);

        mockMvc.perform(put("/core/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated")))
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    @DisplayName("PUT /core/tasks/{id} returns 404 when not found")
    void updateTask_notFound() throws Exception {
        Task request = new Task(null, "Updated", true);

        given(taskService.update(eq(1L), any(Task.class))).willReturn(null);

        mockMvc.perform(put("/core/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /core/tasks/{id} returns 204")
    void deleteTask() throws Exception {
        willDoNothing().given(taskService).delete(1L);

        mockMvc.perform(delete("/core/tasks/1"))
                .andExpect(status().isNoContent());
    }
}
