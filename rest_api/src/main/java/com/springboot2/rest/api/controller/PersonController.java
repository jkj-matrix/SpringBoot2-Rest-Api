package com.springboot2.rest.api.controller;

import com.springboot2.rest.api.dto.PersonResponse;
import com.springboot2.rest.api.entity.Person;
import com.springboot2.rest.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public PersonResponse getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonResponse getPerson(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }

    @PostMapping()
    public PersonResponse savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PutMapping("/{id}")
    public PersonResponse updatePerson(@PathVariable("id") Long id, @RequestBody Person person){
        person.setId(id);
        return personService.updatePerson(person);
    }

}
