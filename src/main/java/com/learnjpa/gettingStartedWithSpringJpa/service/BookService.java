package com.learnjpa.gettingStartedWithSpringJpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.mapper;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.BookRequestDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.BookResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Author;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Book;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Category;
import com.learnjpa.gettingStartedWithSpringJpa.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());

        if (bookRequestDto.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("You need at least one author");
        } else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookRequestDto.getAuthorIds()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }

        if (bookRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("book atleast one category");
        }

        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        Book book1 = bookRepository.save(book);

        return mapper.bookToBookResponseDto(book1);
    }

    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);

        return mapper.bookToBookResponseDto(book);

    }

    public Book getBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + bookId));

        return book;
    }

    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.booksToBookResponseDtos(books);
    }

    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);

        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto){
        Book bookToEdit =getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if(!bookRequestDto.getAuthorIds().isEmpty()){
            List<Author> authors= new ArrayList<>( );
            for (Long authorId: bookRequestDto.getAuthorIds()){
                Author author= authorService.getAuthor(authorId);

                authors.add(author);
            }
            bookToEdit.setAuthors(authors);

            if(bookRequestDto.getCategoryId()!=null){
                Category category=categoryService.getCategory(bookRequestDto.getCategoryId());

                bookToEdit.setCategory(category);

            }


        }
        return mapper.bookToBookResponseDto(bookToEdit);


    }


    public BookResponseDto addAuthorToBook(Long bookId, Long authorId){
        Book book=getBook(bookId);
        Author author=authorService.getAuthor(authorId);
        if(author.getBooks().contains(book)){
            throw new IllegalArgumentException("This author is already assigned to this book.");
        }
        book.addAuthor(author);
        author.addBook(book);

        return mapper.bookToBookResponseDto(book);
    }

    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId){
        Book book =getBook(bookId);

        Author author=authorService.getAuthor(authorId);
        if(!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("Book does not have this author.");
        }
        author.removeBook(book);
        book.deleteAuthor(author);

        return mapper.bookToBookResponseDto(book);
    }

    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId){
        Book book = getBook(bookId);
        Category category=categoryService.getCategory(categoryId);

        if(Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("Book already has a category");
        }

        book.setCategory(category);
        category.addBook(book);

        return mapper.bookToBookResponseDto(book);
    }

    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId){
        Book book=getBook(bookId);
        Category category= categoryService.getCategory(categoryId);

        if(!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book does not have a category to delete.");
        }
        book.setCategory(null);
        category.removeBook(book);
        return mapper.bookToBookResponseDto(book);
    }
}
