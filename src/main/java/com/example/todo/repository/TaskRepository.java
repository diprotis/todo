package com.example.todo.repository;

import com.example.todo.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Interface to represent the repository for the entity {@link TaskEntity}
 */
@Repository
@Transactional
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}
