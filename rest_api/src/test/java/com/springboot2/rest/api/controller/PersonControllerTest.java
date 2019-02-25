package com.springboot2.rest.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot2.rest.api.constant.Status;
import com.springboot2.rest.api.dto.PersonResponse;
import com.springboot2.rest.api.entity.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    Person p2 = new Person();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private String baseUrl = "http://localhost:8080";

    @Before
    public void setup() throws ParseException {
        baseUrl = baseUrl + "/person";
        p2.setFullName("Mary Sy");
        p2.setGender("F");
        Date d2 = sdf.parse("2012-01-23");
        p2.setDateOfBirth(d2);
    }

    @Test
    public void getAllPersons() throws Exception{
        ResultActions actions = mvc.perform(get(baseUrl + "/all").accept(MediaType.APPLICATION_JSON));
        PersonResponse response = fromJsonResult(actions.andReturn(), PersonResponse.class);

        Assert.assertEquals(HttpStatus.OK.value(), actions.andReturn().getResponse().getStatus());
        Assert.assertEquals(5, response.getPersons().size());
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());

    }

    @Test
    public void getPerson() throws Exception{
        ResultActions actions = mvc.perform(get(baseUrl + "/1").accept(MediaType.APPLICATION_JSON));
        PersonResponse response = fromJsonResult(actions.andReturn(), PersonResponse.class);

        Assert.assertEquals(HttpStatus.OK.value(), actions.andReturn().getResponse().getStatus());
        Assert.assertEquals(1, response.getPersons().size());
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
    }

    @Test
    public void savePerson() throws Exception{
        p2.setGender("F");
        Date d2 = sdf.parse("2012-01-23");
        p2.setDateOfBirth(d2);

        String personJson = toJson(p2);
        ResultActions actions = mvc.perform(post(baseUrl).content(personJson).contentType(MediaType.APPLICATION_JSON));
        PersonResponse response = fromJsonResult(actions.andReturn(), PersonResponse.class);

        Assert.assertEquals(HttpStatus.OK.value(), actions.andReturn().getResponse().getStatus());
        Assert.assertEquals(1, response.getPersons().size());
        Assert.assertEquals("Mary Sy", response.getPersons().get(0).getFullName());
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
        Assert.assertNull(response.getError());
    }

    @Test
    public void updatePerson() throws Exception{
        p2.setId(3L);
        p2.setFullName("Updated Mary Sy");
        p2.setGender("F");
        Date d2 = sdf.parse("2012-01-23");
        p2.setDateOfBirth(d2);

        String personJson = toJson(p2);
        ResultActions actions = mvc.perform(put(baseUrl+"/"+p2.getId()).content(personJson).contentType(MediaType.APPLICATION_JSON));
        PersonResponse response = fromJsonResult(actions.andReturn(), PersonResponse.class);

        Assert.assertEquals(HttpStatus.OK.value(), actions.andReturn().getResponse().getStatus());
        Assert.assertEquals(1, response.getPersons().size());
        Assert.assertEquals(new Long(3L), response.getPersons().get(0).getId());
        Assert.assertEquals("Updated Mary Sy", response.getPersons().get(0).getFullName());
        Assert.assertEquals(response.getStatus(), Status.success.getStatus());
        Assert.assertNull(response.getError());

    }

    private String toJson(Object object) throws Exception {
        return this.mapper.writeValueAsString(object);
    }

    <T> T fromJsonResult(MvcResult result, Class<T> tClass) throws Exception {
        return this.mapper.readValue(result.getResponse().getContentAsString(), tClass);
    }

}