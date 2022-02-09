package com.example.todo.dao;

import com.example.todo.model.Person;
import org.springframework.stereotype.Repository;

/**
 * DAO interface to interact with the Person records.
 */
@Repository
public interface PersonDao {

    /**
     * Method that fetches the Person details for the given "id".
     *
     * @param id id
     * @return person details
     */
    Person getPerson(Long id);

    /**
     * Method that adds a new "Person" to the database.
     *
     * @param person person
     * @return saved person information
     */
    Person addPerson(Person person);

    /**
     * Method that updates an existing "Person" record in the database.
     *
     * @param person person
     * @return updated person information
     */
    Person updatePerson(Person person);

    /**
     * Method that deletes the "Person" record for the given "id".
     *
     * @param id id
     * @return flag whether the record was deleted (or not)
     */
    boolean deletePerson(Long id);

}
