package com.learnjpa.gettingStartedWithSpringJpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjpa.gettingStartedWithSpringJpa.repository.CategoryRepository;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        private final CategoryRepository categoryRepository;

        public CategoryService(CategoryRepository categoryRepository){
            this.categoryRepository=categoryRepository;
        }
    }

    public Category getCategory(Long categoryId){
        return null;
    }

}
