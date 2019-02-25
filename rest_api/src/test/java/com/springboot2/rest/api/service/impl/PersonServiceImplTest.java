package com.springboot2.rest.api.service.impl;

import com.springboot2.rest.api.constant.Status;
import com.springboot2.rest.api.dao.PersonRepository;
import com.springboot2.rest.api.dto.PersonResponse;
import com.springboot2.rest.api.entity.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    List<Person> persons = new ArrayList<>();
    Person p1 = new Person();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setup() throws ParseException {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(personService, "idNotFound", "Person Id {0} not found.");

        p1.setFullName("John Paul");
        p1.setGender("M");
        p1.setDateOfBirth(sdf.parse("1998-01-23"));

        Person p2 = new Person();
        p2.setFullName("Mary Sy");
        p2.setGender("F");
        Date d2 = sdf.parse("2012-01-23");
        p2.setDateOfBirth(d2);

        Person p3 = new Person();
        p3.setFullName("Ryan Anderson");
        p3.setGender("M");
        Date d3 = sdf.parse("1985-08-22");
        p3.setDateOfBirth(d3);

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        p1.setId(1L);
    }

    @Test
    public void getAllPersons() {
        Mockito.when(personRepository.findAll()).thenReturn(persons);
        PersonResponse response = personService.getAllPersons();
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
        Assert.assertEquals(response.getPersons().size(), 3);
    }

    @Test
    public void getPersonById() throws ParseException {
        Optional<Person> opt = Optional.of(p1);

        Mockito.when(personRepository.findById(p1.getId())).thenReturn(opt);

        PersonResponse response = personService.getPersonById(1L);
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
        Assert.assertEquals(response.getPersons().size(), 1);
        Assert.assertEquals(response.getPersons().get(0).getFullName(), "John Paul");
    }

    @Test
    public void getPersonById_Failed() throws ParseException {
        Optional<Person> opt = Optional.of(p1);

        Mockito.when(personRepository.findById(p1.getId())).thenReturn(opt);

        PersonResponse response = personService.getPersonById(2L);
        Assert.assertEquals(response.getStatus(), Status.failed.getStatus());
        Assert.assertEquals(response.getPersons().size(), 0);
        Assert.assertEquals(response.getError().getMessage(), "Person Id 2 not found.");
    }

    @Test
    public void savePerson() throws ParseException {
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(Mockito.any());

        PersonResponse response = personService.savePerson(p1);
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
    }

    @Test
    public void updatePerson() throws ParseException {
        Optional<Person> opt = Optional.of(p1);

        Mockito.when(personRepository.findById(p1.getId())).thenReturn(opt);

        PersonResponse response = personService.updatePerson(p1);
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
    }

    @Test
    public void updatePerson_Failed() throws ParseException {
        Optional<Person> opt = Optional.of(p1);
        Mockito.when(personRepository.findById(p1.getId())).thenReturn(opt);

        p1.setId(2L);
        PersonResponse response = personService.updatePerson(p1);
        Assert.assertEquals(response.getStatus(), Status.failed.getStatus());
        Assert.assertEquals(response.getError().getMessage(), "Person Id 2 not found.");
    }
}