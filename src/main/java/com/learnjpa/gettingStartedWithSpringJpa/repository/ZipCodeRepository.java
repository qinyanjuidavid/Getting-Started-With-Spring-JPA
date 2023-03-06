package com.learnjpa.gettingStartedWithSpringJpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjpa.gettingStartedWithSpringJpa.entity.ZipCode;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Long>{
    
}
