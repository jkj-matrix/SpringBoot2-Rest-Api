package com.springboot2.rest.api.dao;

import com.springboot2.rest.api.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends GenericRepository<Person, Long> {}
