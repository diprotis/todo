package com.example.todo.service.impl;

import com.example.todo.constants.ApplicationConstants;
import com.example.todo.dao.PersonDao;
import com.example.todo.exceptions.BusinessException;
import com.example.todo.mappers.PersonMapper;
import com.example.todo.model.Person;
import com.example.todo.model.Role;
import com.example.todo.request.AddPersonRequest;
import com.example.todo.response.PersonResponse;
import com.example.todo.response.UpdatePersonRequest;
import com.example.todo.service.PersonService;
import com.example.todo.util.ValidationUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Service class that implements {@link PersonService}
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method that fetches the "Person" response for the given "id".
     *
     * @param id id
     * @return personResponse
     */
    @Override
    public PersonResponse getPerson(@NonNull final Long id) {
        try {
            final Person person = personDao.getPerson(id);
            log.info("Getting person details for id {} : {}", id, person);
            return PersonMapper.INSTANCE.toPersonResponse(person);
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.GET_PERSON_FAILED, id, e.getMessage())).build();
        }
    }

    /**
     * Method that adds a new "Person".
     *
     * @param addPersonRequest addPersonRequest
     * @return neely created Person details
     */
    @Override
    public PersonResponse addPerson(@NonNull final AddPersonRequest addPersonRequest) {
        try {
            final Person person = Person.builder().name(addPersonRequest.getName())
                    .email(addPersonRequest.getEmail()).password(passwordEncoder.encode(addPersonRequest.getPassword()))
                    .role(Optional.ofNullable(addPersonRequest.getRole()).map(Role::valueOf).orElse(Role.USER)).build();
            log.info("Adding person details {}", person);
            return PersonMapper.INSTANCE.toPersonResponse(personDao.addPerson(person));
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.ADD_PERSON_FAILED, addPersonRequest, e.getMessage()))
                    .build();
        }
    }

    /**
     * Method that updates an existing "Person".
     *
     * @param updatePersonRequest updatePersonRequest
     * @return updated Person details
     */
    @Override
    public PersonResponse updatePerson(@NonNull final UpdatePersonRequest updatePersonRequest) {
        try {
            log.info("Trying to update the person: {} with details: {}",
                    updatePersonRequest.getId(), updatePersonRequest);
            validateUpdatePersonRequest(updatePersonRequest);
            final Person person = personDao.getPerson(updatePersonRequest.getId());
            if (Objects.nonNull(person)) {
                if (StringUtils.isNotEmpty(updatePersonRequest.getName())) {
                    person.setName(updatePersonRequest.getName());
                }
                if (StringUtils.isNotEmpty(updatePersonRequest.getEmail())) {
                    person.setEmail(updatePersonRequest.getEmail());
                }
                if (StringUtils.isNotEmpty(updatePersonRequest.getPassword())) {
                    person.setPassword(updatePersonRequest.getPassword());
                }
            }
            log.info("Update person id: {} with: {}", updatePersonRequest.getId(), person);
            return PersonMapper.INSTANCE.toPersonResponse(personDao.updatePerson(person));
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.UPDATE_PERSON_FAILED,
                            updatePersonRequest.getId(), e.getMessage()))
                    .build();
        }
    }

    /**
     * Method that deletes an existing "Person" for the given "id".
     *
     * @param id id
     * @return status whether the Person entity was deleted (or not)
     */
    @Override
    public String deletePerson(final Long id) {
        try {
            final boolean deletionStatus = personDao.deletePerson(id);
            return (deletionStatus) ? ApplicationConstants.SUCCESS : ApplicationConstants.FAILURE;
        } catch (Exception e) {
            throw BusinessException.builder().errorCode(ApplicationConstants.ErrorCode.BAD_REQUEST_ERROR_CODE)
                    .errorDescription(String.format(ApplicationConstants.ErrorMessage.GET_PERSON_FAILED, id, e.getMessage()))
                    .build();
        }
    }

    private void validateUpdatePersonRequest(@NonNull final UpdatePersonRequest updatePersonRequest) {
        if (StringUtils.isNotEmpty(updatePersonRequest.getEmail())) {
            Validate.isTrue(ValidationUtil.isValidEmail(updatePersonRequest.getEmail()), "email should be valid");
        }
        if (StringUtils.isNotEmpty(updatePersonRequest.getPassword())) {
            Validate.isTrue(updatePersonRequest.getPassword().length() >= ApplicationConstants.MIN_PASSWORD_LENGTH,
                    "password should be minimum 8 characters.");
        }
    }

}
