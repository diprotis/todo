package com.example.todo.controller;

import com.example.todo.request.AddTaskRequest;
import com.example.todo.request.UpdateTaskRequest;
import com.example.todo.response.TaskResponse;
import com.example.todo.service.TaskService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that handles the requests related to the Task domain.
 */
@Slf4j
@RestController
@RequestMapping("/api/task")
public class TaskController {

    private static final String ID_VARIABLE = "id";

    private static final String ID_PATH = "/{id}";
    public static final String GET_ALL_PATH = "/all";

    @Autowired
    private TaskService taskService;

    /**
     * Method that fetches the "Task" response for the given "id".
     *
     * @param id id
     * @return taskResponse
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = ID_PATH)
    public ResponseEntity<TaskResponse> getTask(@PathVariable(value = ID_VARIABLE) final Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    /**
     * Method that fetches all the tasks for a given person "id".
     *
     * @param personId personId
     * @return list of all tasks
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = GET_ALL_PATH)
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam(value = "personId") final Long personId) {
        return ResponseEntity.ok(taskService.getAllTasks(personId));
    }

    /**
     * Method that adds a new "addTaskRequest".
     *
     * @param addTaskRequest addTaskRequest
     * @return neely created Task details
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> addTask(
            @RequestBody @Validated @NonNull final AddTaskRequest addTaskRequest) {
        return ResponseEntity.ok(taskService.addTask(addTaskRequest));
    }

    /**
     * Method that updates an existing "Task".
     *
     * @param updateTaskRequest updateTaskRequest
     * @return updated Task details
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = ID_PATH)
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable(value = ID_VARIABLE) final Long id,
            @RequestBody @Validated @NonNull final UpdateTaskRequest updateTaskRequest) {
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest));
    }

    /**
     * Method that deletes an existing "Task".
     *
     * @param id id
     * @return status whether the entity was deleted (or not)
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = ID_PATH)
    public ResponseEntity<String> deleteTask(@PathVariable(value = ID_VARIABLE) final Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
