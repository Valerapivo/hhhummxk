package com.example.api_gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final RestTemplate restTemplate;
    private final String CORE_BASE_URL = "http://core-service:8081/core/tasks";

    public TaskController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return restTemplate.postForObject(CORE_BASE_URL, task, Task.class);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        Task[] tasks = restTemplate.getForObject(CORE_BASE_URL, Task[].class);
        return tasks == null ? List.of() : Arrays.asList(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        ResponseEntity<Task> response =
                restTemplate.getForEntity(CORE_BASE_URL + "/" + id, Task.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody Task updatedTask) {
        restTemplate.put(CORE_BASE_URL + "/" + id, updatedTask);
        Task task = restTemplate.getForObject(CORE_BASE_URL + "/" + id, Task.class);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        restTemplate.delete(CORE_BASE_URL + "/" + id);
        return ResponseEntity.noContent().build();
    }
}