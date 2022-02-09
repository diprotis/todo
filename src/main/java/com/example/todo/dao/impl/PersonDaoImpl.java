package com.example.todo.dao.impl;

import com.example.todo.constants.ApplicationConstants;
import com.example.todo.dao.PersonDao;
import com.example.todo.entity.PersonEntity;
import com.example.todo.mappers.PersonMapper;
import com.example.todo.model.Person;
import com.example.todo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * DAO Class that implements {@link PersonDao}
 */
@Slf4j
@Component
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private PersonRepository personRepository;


    /**
     * Method that fetches the Person details for the given "id".
     *
     * @param id id
     * @return person details
     */
    @Override
    public Person getPerson(final Long id) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        log.info("Fetching the personEntity for id {} : {}", id, personEntityOptional);
        if (personEntityOptional.isPresent()) {
            return PersonMapper.INSTANCE.fromPersonEntity(personEntityOptional.get());
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .PERSON_DOES_NOT_EXIST_IN_DATABASE, id));
    }

    /**
     * Method that adds a new "Person" to the database.
     *
     * @param person person
     * @return saved person information
     */
    @Override
    public Person addPerson(final Person person) {
        final PersonEntity personEntity = PersonMapper.INSTANCE.fromPerson(person);
        log.info("Adding the personEntity to the database: {}", personEntity);
        return PersonMapper.INSTANCE.fromPersonEntity(personRepository.save(personEntity));
    }

    /**
     * Method that updates an existing "Person" record in the database.
     *
     * @param person person
     * @return updated person information
     */
    @Override
    public Person updatePerson(final Person person) {
        final boolean checkIfExists = personRepository.existsById(person.getId());
        if (checkIfExists) {
            final PersonEntity personEntity = PersonMapper.INSTANCE.fromPerson(person);
            log.info("Updating the existing personEntity with id: {} to: {}", personEntity.getId(), personEntity);
            return PersonMapper.INSTANCE.fromPersonEntity(personRepository.save(personEntity));
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .PERSON_DOES_NOT_EXIST_IN_DATABASE, person.getId()));
    }

    /**
     * Method that deletes the "Person" record for the given "id".
     *
     * @param id id
     * @return flag whether the record was deleted (or not)
     */
    @Override
    public boolean deletePerson(final Long id) {
        final boolean checkIfExists = personRepository.existsById(id);
        if (checkIfExists) {
            log.info("Deleting the existing personEntity with id: {}", id);
            personRepository.deleteById(id);
            return Boolean.TRUE;
        }
        throw new IllegalArgumentException(String.format(ApplicationConstants.ErrorMessage
                .PERSON_DOES_NOT_EXIST_IN_DATABASE, id));
    }
}
