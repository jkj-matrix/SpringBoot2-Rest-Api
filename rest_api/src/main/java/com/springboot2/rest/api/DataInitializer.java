package com.springboot2.rest.api;

import com.springboot2.rest.api.dao.PersonRepository;
import com.springboot2.rest.api.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private PersonRepository personRepository;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = personRepository.count();

        if (count == 0) {
            Person p1 = new Person();
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

            Person p4 = new Person();
            p4.setFullName("Rod Drew");
            p4.setGender("F");
            Date d4 = sdf.parse("2009-03-12");
            p4.setDateOfBirth(d4);

            Person p5 = new Person();
            p5.setFullName("Sally Copper");
            p5.setGender("M");
            Date d5 = sdf.parse("1985-11-30");
            p5.setDateOfBirth(d5);

            personRepository.save(p1);
            personRepository.save(p2);
            personRepository.save(p3);
            personRepository.save(p4);
            personRepository.save(p5);
        }

    }
}
