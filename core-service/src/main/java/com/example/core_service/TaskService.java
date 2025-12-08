package com.example.core_service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task update(Long id, Task updated) {
        return taskRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setCompleted(updated.isCompleted());
                    return taskRepository.save(existing);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}