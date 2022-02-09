package com.example.todo.service;

import com.example.todo.request.AddTaskRequest;
import com.example.todo.request.UpdateTaskRequest;
import com.example.todo.response.TaskResponse;

import java.util.List;

/**
 * Interface that handles the functionalities of the task.
 */
public interface TaskService {

    /**
     * Method that returns the Task details for a given "id".
     *
     * @param id id
     * @return task response
     */
    TaskResponse getTask(Long id);

    /**
     * Method that returns the list of all Tasks for a given "personId".
     *
     * @param personId personId
     * @return list of all tasks
     */
    List<TaskResponse> getAllTasks(Long personId);

    /**
     * Method that adds a new "Task" to the database.
     *
     * @param addTaskRequest addTaskRequest
     * @return neely added task details
     */
    TaskResponse addTask(AddTaskRequest addTaskRequest);

    /**
     * Method that updates an existing "Task".
     *
     * @param updateTaskRequest updateTaskRequest
     * @return updated task details
     */
    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest);

    /**
     * Method that deletes an existing "Task".
     *
     * @param id id
     * @return status whether the particular entry was deleted (or not)
     */
    String deleteTask(Long id);
}
