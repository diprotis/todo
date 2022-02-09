package com.example.todo.dao;

import com.example.todo.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO interface to interact with the Task records.
 */
@Repository
public interface TaskDao {

    /**
     * Method that fetches the Task details for a given "id".
     *
     * @param id id
     * @return task information
     */
    Task getTask(Long id);

    /**
     * Method that fetches all the tasks for a given "Person".
     *
     * @param personId personId
     * @return list of tasks
     */
    List<Task> getAllTasks(Long personId);

    /**
     * Method that adds a new "Task" to the database.
     *
     * @param task task
     * @return saved Task
     */
    Task addTask(Task task);

    /**
     * Method that updates an existing "Task" record in the database.
     *
     * @param task task
     * @return updated task
     */
    Task updateTask(Task task);

    /**
     * Method that deletes an existing "Task" record for a given "id".
     *
     * @param id id
     * @return flag whether the record is deleted (or not)
     */
    boolean deleteTask(Long id);
}
