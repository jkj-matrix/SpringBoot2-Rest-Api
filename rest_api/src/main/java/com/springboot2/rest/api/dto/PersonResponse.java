package com.springboot2.rest.api.dto;

import com.springboot2.rest.api.entity.Person;

import java.util.List;

public class PersonResponse {

    public PersonResponse(){}

    private String status;
    private List<Person> persons;
    private Error error;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
