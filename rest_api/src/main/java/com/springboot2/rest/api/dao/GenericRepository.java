package com.springboot2.rest.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
interface GenericRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    //Override Iterable to List
    List<T> findAll();
}
