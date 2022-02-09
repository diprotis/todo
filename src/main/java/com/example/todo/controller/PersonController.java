package com.example.todo.controller;

import com.example.todo.request.AddPersonRequest;
import com.example.todo.response.PersonResponse;
import com.example.todo.response.UpdatePersonRequest;
import com.example.todo.service.PersonService;
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
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller that handles the requests related to the Person domain.
 */
@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    private static final String ID_VARIABLE = "id";

    private static final String ID_PATH = "/{id}";

    @Autowired
    private PersonService personService;

    /**
     * Method that fetches the "Person" response for the given "id".
     *
     * @param id id
     * @return personResponse
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = ID_PATH)
    public ResponseEntity<PersonResponse> getPerson(@PathVariable(value = ID_VARIABLE) final Long id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    /**
     * Method that adds a new "Person".
     *
     * @param addPersonRequest addPersonRequest
     * @return neely created Person details
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> addPerson(
            @RequestBody @Validated @NonNull final AddPersonRequest addPersonRequest) {
        return ResponseEntity.ok(personService.addPerson(addPersonRequest));
    }

    /**
     * Method that updates an existing "Person".
     *
     * @param updatePersonRequest updatePersonRequest
     * @return updated Person details
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = ID_PATH)
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable(value = ID_VARIABLE) final Long id,
            @RequestBody @Validated @NonNull final UpdatePersonRequest updatePersonRequest) {
        return ResponseEntity.ok(personService.updatePerson(updatePersonRequest));
    }

    /**
     * Method that deletes an existing "Person".
     *
     * @param id id
     * @return status whether the entity was deleted (or not)
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = ID_PATH)
    public ResponseEntity<String> deletePerson(@PathVariable(value = ID_VARIABLE) final Long id) {
        return ResponseEntity.ok(personService.deletePerson(id));
    }

}
