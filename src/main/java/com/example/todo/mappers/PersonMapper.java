package com.example.todo.mappers;

import com.example.todo.entity.PersonEntity;
import com.example.todo.model.Person;
import com.example.todo.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper class for converting the application and entity model for Person.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    /**
     * Instance to access mapper.
     */
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    /**
     * Method that converts the Person entity model to the application model.
     *
     * @param personEntity personEntity
     * @return application model
     */
    Person fromPersonEntity(PersonEntity personEntity);

    /**
     * Method that converts the Person model to the entity model.
     *
     * @param person application model
     * @return entity model
     */
    PersonEntity fromPerson(Person person);

    /**
     * Method that converts the Person model to the response model.
     *
     * @param person application model
     * @return response model
     */
    PersonResponse toPersonResponse(Person person);
}
