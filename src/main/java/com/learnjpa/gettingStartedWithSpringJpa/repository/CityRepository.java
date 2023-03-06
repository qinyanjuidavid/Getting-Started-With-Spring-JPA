package com.learnjpa.gettingStartedWithSpringJpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjpa.gettingStartedWithSpringJpa.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long>  {
    
}
