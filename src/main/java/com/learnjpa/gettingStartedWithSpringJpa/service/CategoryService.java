package com.learnjpa.gettingStartedWithSpringJpa.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.mapper;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.CategoryRequestDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.CategoryResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Category;
import com.learnjpa.gettingStartedWithSpringJpa.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find category with id."));
    }

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto){
        Category category= new Category();
        category.setName(categoryRequestDto.getName());
        categoryRepository.save(category);

        return mapper.categoryToCategoryDto(category);
    }

    public CategoryResponseDto getCategoryById(Long categoryId){
        Category category=getCategory(categoryId);

        return mapper.categoryToCategoryDto(category);
    }

    public List<CategoryResponseDto> getCategories(){
        List<Category> categories= StreamSupport.stream(categoryRepository.findAll().spliterator(),false).collect(Collectors.toList());

        return mapper.categoriesToCategoryResponseDtos(categories);
    }


    public CategoryResponseDto deleteCategory(Long categoryId){
        Category category=getCategory(categoryId);
        categoryRepository.delete(category);
        return mapper.categoryToCategoryDto(category);
    }

    @Transactional
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto){
        Category categoryToEdit= getCategory(categoryId);
        categoryToEdit.setName(categoryRequestDto.getName());
        return mapper.categoryToCategoryDto(categoryToEdit);
    }


}
