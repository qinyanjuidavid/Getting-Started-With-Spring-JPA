package com.learnjpa.gettingStartedWithSpringJpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.CategoryRequestDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.AuthorResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.CategoryResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody final CategoryRequestDto categoryRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);

        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable final Long categoryId){
        CategoryResponseDto categoryResponseDto= categoryService.getCategoryById(categoryId);

        return new ResponseEntity<CategoryResponseDto>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(@PathVariable final Long categoryId){
        List<CategoryResponseDto> categoryResponseDto= categoryService.getCategories();
        
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }


    
}
