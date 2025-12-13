package com.example.core_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(1L, "Test", false);
    }

    @Test
    @DisplayName("create() saves task via repository")
    void create() {
        Task toCreate = new Task(null, "New", false);
        when(taskRepository.save(any(Task.class)))
                .thenReturn(new Task(1L, "New", false));

        Task result = taskService.create(toCreate);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("New");
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    @DisplayName("getAll() returns list from repository")
    void getAll() {
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> result = taskService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test");
    }

    @Test
    @DisplayName("getById() returns task when present")
    void getById_found() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getById() returns null when not found")
    void getById_notFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task result = taskService.getById(1L);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("update() updates existing task")
    void update_found() {
        Task updated = new Task(null, "Updated", true);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.update(1L, updated);

        assertThat(result.getTitle()).isEqualTo("Updated");
        assertThat(result.isCompleted()).isTrue();
    }

    @Test
    @DisplayName("update() returns null when task not found")
    void update_notFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task result = taskService.update(1L, new Task());

        assertThat(result).isNull();
        verify(taskRepository, never()).save(any());
    }

    @Test
    @DisplayName("delete() delegates to repository")
    void delete() {
        taskService.delete(1L);

        verify(taskRepository).deleteById(1L);
    }
}
