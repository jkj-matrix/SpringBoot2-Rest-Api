package com.springboot2.rest.api.service;

import com.springboot2.rest.api.dto.PersonResponse;
import com.springboot2.rest.api.entity.Person;

public interface PersonService {

    PersonResponse getAllPersons();

    PersonResponse getPersonById(Long personId);

    PersonResponse savePerson(Person person);

    PersonResponse updatePerson(Person person);

}
