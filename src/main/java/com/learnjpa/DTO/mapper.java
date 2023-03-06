package com.learnjpa.DTO;

import java.util.ArrayList;
import java.util.List;

import com.learnjpa.DTO.responseDto.BookResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Author;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Book;

public class mapper {
    public static BookResponseDto BookResponseDto(Book  book){
        BookResponseDto bookResponseDto=new  BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setCategoryName(book.getCategory().getName());;
        
        List<String> names= new ArrayList<>();
        List<Author> authors = new ArrayList<>();

        for(Author author: authors){
            names.add(author.getName());
        }

        bookResponseDto.setAuthorNames(names);

        return bookResponseDto;

    }
    
}
