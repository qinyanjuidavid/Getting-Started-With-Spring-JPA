package com.learnjpa.gettingStartedWithSpringJpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjpa.gettingStartedWithSpringJpa.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

}
