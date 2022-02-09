package com.example.todo.mappers;

import com.example.todo.entity.TaskEntity;
import com.example.todo.model.Task;
import com.example.todo.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper class for converting the application and entity model for Person.
 */
@Mapper
public interface TaskMapper {

    /**
     * Instance to access mapper.
     */
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    /**
     * Method that converts the Task entity model to the application model.
     *
     * @param taskEntity taskEntity
     * @return application model
     */
    Task fromTaskEntity(TaskEntity taskEntity);

    /**
     * Method that converts the Task model to the entity model.
     *
     * @param task application model
     * @return entity model
     */
    TaskEntity fromTask(Task task);

    /**
     * Method that converts the Task model to the response model.
     *
     * @param task application model
     * @return response model
     */
    TaskResponse toTaskResponse(Task task);
}
