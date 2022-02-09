package com.example.todo.service;

import com.example.todo.request.AddPersonRequest;
import com.example.todo.response.PersonResponse;
import com.example.todo.response.UpdatePersonRequest;

/**
 * Interface that handles the functionalities of the task.
 */
public interface PersonService {

    /**
     * Method that fetches the "Person" response for the given "id".
     *
     * @param id id
     * @return personResponse
     */
    PersonResponse getPerson(Long id);

    /**
     * Method that adds a new "Person".
     *
     * @param addPersonRequest addPersonRequest
     * @return neely created Person details
     */
    PersonResponse addPerson(AddPersonRequest addPersonRequest);

    /**
     * Method that updates an existing "Person".
     *
     * @param updatePersonRequest updatePersonRequest
     * @return updated Person details
     */
    PersonResponse updatePerson(UpdatePersonRequest updatePersonRequest);

    /**
     * Method that deletes an existing "Person" for the given "id".
     *
     * @param id id
     * @return status whether the Person entity was deleted (or not)
     */
    String deletePerson(final Long id);
}
