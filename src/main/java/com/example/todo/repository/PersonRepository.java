package com.example.todo.repository;

import com.example.todo.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Interface to represent the repository for the entity {@link PersonEntity}
 */
@Repository
@Transactional
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
