package com.learnjpa.gettingStartedWithSpringJpa.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.mapper;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.AuthorRequestDto;
import com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto.AuthorResponseDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.Author;
import com.learnjpa.gettingStartedWithSpringJpa.entity.ZipCode;
import com.learnjpa.gettingStartedWithSpringJpa.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ZipCodeService zipCodeService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, ZipCodeService zipCodeService) {
        this.authorRepository = authorRepository;
        this.zipCodeService = zipCodeService;
    }

    @Transactional
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());

        if (authorRequestDto.getZipcodeId() == null) {
            throw new IllegalArgumentException("author needs a zipcode");
        }
        ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipCode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.authorsToAuthorResponseDto(authors);
    }

    public AuthorResponseDto getAuthorById(Long authorId) {
        return mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    public Author getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException("Author with id: " + authorId + " could not be found."));

        return author;
    }

    public AuthorResponseDto deleteAuthor(Long authId) {
        Author author = getAuthor(authId);

        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);

    }

    @Transactional
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipcodeId() != null) {
            ZipCode zipcode = zipCodeService.getZipCode(authorRequestDto.getZipcodeId());
            authorToEdit.setZipcode(zipcode);
        }
        return mapper.authorToAuthorResponseDto(authorToEdit);
    }

    @Transactional
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId){
        Author author =getAuthor(authorId);
        ZipCode zipCode= zipCodeService.getZipCode(zipcodeId);
        if(Objects.nonNull(author.getZipcode())){
            throw new RuntimeException("Author already has a zipcode");
        }
        author.setZipcode(zipCode);
        return mapper.authorToAuthorResponseDto(author);
   }

   @Transactional
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId){
        Author author=getAuthor(authorId);
        author.setZipcode(null);
        return mapper.authorToAuthorResponseDto(author);
    }
}
