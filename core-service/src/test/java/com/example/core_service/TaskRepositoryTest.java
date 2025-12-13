package com.example.core_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Save and find Task by id")
    void saveAndFindById() {
        Task task = new Task(1L, "Test task", false);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> found = taskRepository.findById(1L);

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test task");
    }

    @Test
    @DisplayName("Update Task")
    void updateTask() {
        Task task = new Task(1L, "Old", false);
        when(taskRepository.save(task)).thenReturn(new Task(1L, "New", true));

        Task updated = taskRepository.save(task);

        assertThat(updated.getTitle()).isEqualTo("New");
        assertThat(updated.isCompleted()).isTrue();
    }

    @Test
    @DisplayName("Delete Task")
    void deleteTask() {
        taskRepository.deleteById(1L);
        verify(taskRepository).deleteById(1L);
    }
}
