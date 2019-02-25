package com.springboot2.rest.api.service.impl;

import com.springboot2.rest.api.constant.Status;
import com.springboot2.rest.api.dao.PersonRepository;
import com.springboot2.rest.api.dto.Error;
import com.springboot2.rest.api.dto.PersonResponse;
import com.springboot2.rest.api.entity.Person;
import com.springboot2.rest.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Value("${id.not.found}")
    private String idNotFound;

    public PersonResponse getAllPersons(){
        PersonResponse response = new PersonResponse();
        List<Person> persons = personRepository.findAll();
        response.setStatus(Status.success.getStatus());
        response.setPersons(persons);
        return response;

    }

    public PersonResponse getPersonById(Long personId){
        Optional<Person> option = personRepository.findById(personId);
        PersonResponse response = new PersonResponse();
        //For client, to check an empty list response if no record found
        List<Person> persons = new ArrayList<>();
        response.setStatus(Status.success.getStatus());
        if (option.isPresent()) {
            persons.add(option.get());
            response.setPersons(persons);
            return response;
        }
        response.setPersons(persons);
        response.setStatus(Status.failed.getStatus());
        response.setError(new Error(MessageFormat.format(idNotFound, personId)));
        return response;
    }

    public PersonResponse savePerson(Person person){
        PersonResponse response = new PersonResponse();
        List<Person> persons = new ArrayList<>();
        persons.add(personRepository.save(person));
        response.setPersons(persons);
        response.setStatus(Status.success.getStatus());
        return response;
    }

    public PersonResponse updatePerson(Person person){
        PersonResponse response = new PersonResponse();
        if(personRepository.findById(person.getId()).isPresent()){
            List<Person> persons = new ArrayList<>();
            persons.add(personRepository.save(person));
            response.setPersons(persons);
            response.setStatus(Status.success.getStatus());
            return response;
        }
        response.setStatus(Status.failed.getStatus());
        response.setError(new Error(MessageFormat.format(idNotFound, person.getId())));
        return response;
    }

}



