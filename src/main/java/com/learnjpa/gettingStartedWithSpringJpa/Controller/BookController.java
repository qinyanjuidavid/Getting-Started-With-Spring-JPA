package com.learnjpa.gettingStartedWithSpringJpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.BookRequestDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.BookResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody final BookRequestDto bookRequestDto){
        BookResponseDto bookResponseDto= bookService.addBook(bookRequestDto);

        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable final Long bookId){
        BookResponseDto bookResponseDto= bookService.getBookById(bookId);

        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponseDto>> getBooks(){
        List<BookResponseDto> bookResponseDto= bookService.getBooks();
        return new ResponseEntity<>(bookResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable final Long id){
        BookResponseDto bookResponseDto= bookService.deleteBook(id);
        return new ResponseEntity<>(bookResponseDto,HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<BookResponseDto> bookEdit(@PathVariable final Long id, @RequestBody final BookRequestDto bookRequestDto){
        BookResponseDto bookResponseDto= bookService.editBook(id, bookRequestDto);

        return new ResponseEntity<BookResponseDto>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addCategory/{categoryId}/to/{bookId}")
    public ResponseEntity<BookResponseDto> addCategoryToBook(@PathVariable final Long categoryId, @PathVariable final Long bookId){
        BookResponseDto bookResponseDto=bookService.addCategoryToBook(bookId, categoryId);

        return new ResponseEntity<>(bookResponseDto,HttpStatus.OK);
    }

    @PostMapping("/removeCategory/{categoryId}/from/{bookId}")
    public ResponseEntity<BookResponseDto> removeCategoryFromBook(@PathVariable final Long categoryId,@PathVariable final Long bookId){
        BookResponseDto bookResponseDto= bookService.removeCategoryFromBook(bookId, categoryId);

        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addAuthor/{authorId}/to/{bookId}")
    public ResponseEntity<BookResponseDto> addAuthorToBook(@PathVariable final Long bookId, @PathVariable final Long authorCategory){
        BookResponseDto bookResponseDto= bookService.addAuthorToBook(bookId, authorCategory);

        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeAuthor/{authorId}/from/{bookid}")
    public ResponseEntity<BookResponseDto> removeAuthorFromBook(@PathVariable final Long bookId, @PathVariable final Long authorId){
        BookResponseDto bookResponseDto= bookService.deleteAuthorFromBook(bookId, authorId);

        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    
}
