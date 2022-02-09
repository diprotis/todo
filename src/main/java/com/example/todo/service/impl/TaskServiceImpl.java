package com.example.todo.service.impl;

import com.example.todo.constants.ApplicationConstants;
import com.example.todo.dao.PersonDao;
import com.example.todo.dao.TaskDao;
import com.example.todo.exceptions.BusinessException;
import com.example.todo.mappers.TaskMapper;
import com.example.todo.model.Person;
import com.example.todo.model.Status;
import com.example.todo.model.Task;
import com.example.todo.request.AddTaskRequest;
import com.example.todo.request.UpdateTaskRequest;
import com.example.todo.response.TaskResponse;
import com.example.todo.service.TaskService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class that implements {@link TaskService}
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private PersonDao personDao;

    /**
     * Method that returns the Task details for a given "id".
     *
     * @param id id
     * @return task response
     */
    @Override
    public TaskResponse getTask(@NonNull final Long id) {
        try {
            final Task task = taskDao.getTask(id);
            log.info("Getting task details for id {} : {}", id, task);
            return TaskMapper.INSTANCE.toTaskResponse(task);
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.GET_TASK_FAILED, id, e.getMessage())).build();
        }
    }

    /**
     * Method that returns the list of all Tasks for a given "personId".
     *
     * @param personId personId
     * @return list of all tasks
     */
    @Override
    public List<TaskResponse> getAllTasks(@NonNull final Long personId) {
        try {
            log.info("Getting all task details for person: {}", personId);
            return taskDao.getAllTasks(personId).stream().filter(Objects::nonNull).map(TaskMapper.INSTANCE::toTaskResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.GET_PERSON_FAILED, personId, e.getMessage())).build();
        }
    }

    /**
     * Method that adds a new "Task" to the database.
     *
     * @param addTaskRequest addTaskRequest
     * @return neely added task details
     */
    @Override
    public TaskResponse addTask(@NonNull final AddTaskRequest addTaskRequest) {
        try {
            final Person person = personDao.getPerson(addTaskRequest.getPersonId());
            final Task task = Task.builder().person(person).title(addTaskRequest.getTitle())
                    .description(addTaskRequest.getDescription()).completionDate(addTaskRequest.getCompletionDate())
                    .points(addTaskRequest.getPoints()).status(Status.NOT_STARTED).build();
            log.info("Adding task details {} for person: {}", task, person.getId());
            return TaskMapper.INSTANCE.toTaskResponse(taskDao.addTask(task));
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.ADD_TASK_FAILED, addTaskRequest, e.getMessage()))
                    .build();
        }
    }

    /**
     * Method that updates an existing "Task".
     *
     * @param updateTaskRequest updateTaskRequest
     * @return updated task details
     */
    @Override
    public TaskResponse updateTask(@NonNull final UpdateTaskRequest updateTaskRequest) {
        try {
            log.info("Trying to update the task: {} with details: {}",
                    updateTaskRequest.getId(), updateTaskRequest);
            validateUpdateTaskRequest(updateTaskRequest);
            final Task task = taskDao.getTask(updateTaskRequest.getId());
            if (Objects.nonNull(task)) {
                if (StringUtils.isNotEmpty(updateTaskRequest.getTitle())) {
                    task.setTitle(updateTaskRequest.getTitle());
                }
                if (StringUtils.isNotEmpty(updateTaskRequest.getDescription())) {
                    task.setTitle(updateTaskRequest.getDescription());
                }
                if (StringUtils.isNotEmpty(updateTaskRequest.getStatus())) {
                    task.setStatus(Status.valueOf(updateTaskRequest.getStatus()));
                }
                if (Objects.nonNull(updateTaskRequest.getPoints())) {
                    task.setPoints(updateTaskRequest.getPoints());
                }
            }
            log.info("Update task id: {} with: {}", updateTaskRequest.getId(), task);
            return TaskMapper.INSTANCE.toTaskResponse(taskDao.updateTask(task));
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.UPDATE_TASK_FAILED,
                            updateTaskRequest.getId(), e.getMessage()))
                    .build();
        }
    }

    /**
     * Method that deletes an existing "Task".
     *
     * @param id id
     * @return status whether the particular entry was deleted (or not)
     */
    @Override
    public String deleteTask(@NonNull final Long id) {
        try {
            final boolean deletionStatus = taskDao.deleteTask(id);
            return (deletionStatus) ? ApplicationConstants.SUCCESS : ApplicationConstants.FAILURE;
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.GET_TASK_FAILED, id, e.getMessage()))
                    .build();
        }
    }


    private void validateUpdateTaskRequest(final UpdateTaskRequest updateTaskRequest) {
        if (Objects.nonNull(updateTaskRequest.getPoints())) {
            Validate.isTrue(updateTaskRequest.getPoints() > ApplicationConstants.ZERO);
        }
        if (StringUtils.isNotEmpty(updateTaskRequest.getStatus())) {
            Validate.isTrue(Arrays.stream(Status.values())
                    .anyMatch(status -> status.name().equals(updateTaskRequest.getStatus())));
        }
    }


}
