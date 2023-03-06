package com.learnjpa.gettingStartedWithSpringJpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjpa.gettingStartedWithSpringJpa.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    
}
