package com.example.todo.dao.impl;

import com.example.todo.constants.ApplicationConstants;
import com.example.todo.dao.TaskDao;
import com.example.todo.entity.PersonEntity;
import com.example.todo.entity.TaskEntity;
import com.example.todo.mappers.TaskMapper;
import com.example.todo.model.Task;
import com.example.todo.repository.PersonRepository;
import com.example.todo.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DAO class that implements {@link TaskDao}
 */
@Slf4j
@Component
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Method that fetches the Task details for a given "id".
     *
     * @param id id
     * @return task information
     */
    @Override
    public Task getTask(final Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        log.info("Fetching the taskEntity for id {} : {}", id, taskEntityOptional);
        if (taskEntityOptional.isPresent()) {
            return TaskMapper.INSTANCE.fromTaskEntity(taskEntityOptional.get());
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .TASK_DOES_NOT_EXIST_IN_DATABASE, id));
    }

    /**
     * Method that fetches all the tasks for a given "Person".
     *
     * @param personId personId
     * @return list of tasks
     */
    @Override
    public List<Task> getAllTasks(final Long personId) {
        final Optional<PersonEntity> personEntityOptional = personRepository.findById(personId);
        if (personEntityOptional.isPresent()) {
            final List<TaskEntity> tasks = personEntityOptional.get().getTasks();
            log.info("Fetching all tasks for personId: {} with count: {}", personId, tasks.size());
            return tasks.stream().filter(Objects::nonNull).map(TaskMapper.INSTANCE::fromTaskEntity)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .PERSON_DOES_NOT_EXIST_IN_DATABASE, personId));
    }

    /**
     * Method that adds a new "Task" to the database.
     *
     * @param task task
     * @return saved Task
     */
    @Override
    public Task addTask(final Task task) {
        final TaskEntity taskEntity = TaskMapper.INSTANCE.fromTask(task);
        log.info("Adding the taskEntity to the database: {}", taskEntity);
        return TaskMapper.INSTANCE.fromTaskEntity(taskRepository.save(taskEntity));
    }

    /**
     * Method that updates an existing "Task" record in the database.
     *
     * @param task task
     * @return updated task
     */
    @Override
    public Task updateTask(final Task task) {
        final boolean checkIfExists = taskRepository.existsById(task.getId());
        if (checkIfExists) {
            final TaskEntity taskEntity = TaskMapper.INSTANCE.fromTask(task);
            log.info("Updating the existing taskEntity with id: {} to: {}", taskEntity.getId(), taskEntity);
            return TaskMapper.INSTANCE.fromTaskEntity(taskRepository.save(taskEntity));
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .TASK_DOES_NOT_EXIST_IN_DATABASE, task.getId()));
    }

    /**
     * Method that deletes an existing "Task" record for a given "id".
     *
     * @param id id
     * @return flag whether the record is deleted (or not)
     */
    @Override
    public boolean deleteTask(final Long id) {
        final boolean checkIfExists = taskRepository.existsById(id);
        if (checkIfExists) {
            log.info("Deleting the existing taskEntity with id: {}", id);
            taskRepository.deleteById(id);
            return Boolean.TRUE;
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .TASK_DOES_NOT_EXIST_IN_DATABASE, id));
    }
}
