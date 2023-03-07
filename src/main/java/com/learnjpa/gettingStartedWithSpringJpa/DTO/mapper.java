package com.learnjpa.gettingStartedWithSpringJpa.DTO;

import java.util.ArrayList;
import java.util.List;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.AuthorResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.BookResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.CategoryResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Author;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Book;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Category;

public class mapper {
    public static BookResponseDto bookToBookResponseDto(Book  book){
        BookResponseDto bookResponseDto=new  BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setCategoryName(book.getCategory().getName());;
        
        List<String> names= new ArrayList<>();
        List<Author> authors = book.getAuthors();

        for(Author author: authors){
            names.add(author.getName());
        }

        bookResponseDto.setAuthorNames(names);

        return bookResponseDto;

    }

    public static List<BookResponseDto> booksToBookResponseDtos(List<Book> books){
        List<BookResponseDto> bookResponseDtos= new ArrayList<>();
        for(Book book: books){
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());

        List<String> names = new ArrayList<>();
        List<Book> books= author.getBooks();

        for(Book book: books){
            names.add(book.getName());
        }

        authorResponseDto.setBookNames(names);
        return authorResponseDto;
        
    }

    public static List<AuthorResponseDto> authorsToAuthorResponseDto(List<Author> authors){
        List<AuthorResponseDto> authorResponseDto= new ArrayList<>();

        for(Author author : authors){
            authorResponseDto.add(authorToAuthorResponseDto(author));
        }
        return authorResponseDto;
    }

    public static CategoryResponseDto categoryToCategoryDto(Category category){
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();

        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());

        List<String> names= new ArrayList<>();

        List<Book> books = category.getBooks();

        for(Book book: books){
            names.add(book.getName());
        }

        categoryResponseDto.setBookNames(names);

        return categoryResponseDto;


    } 

    public static List<CategoryResponseDto> categoriesToCategoryResponseDtos(List<Category> categories){
        List<CategoryResponseDto> categoryResponseDtos= new ArrayList<>();

        for(Category category: categories){
            categoryResponseDtos.add(categoryToCategoryDto(category));

        }
        return categoryResponseDtos;

    }


    
}
