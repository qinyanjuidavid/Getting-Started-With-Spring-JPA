package com.learnjpa.gettingStartedWithSpringJpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjpa.gettingStartedWithSpringJpa.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long>{
    
}
