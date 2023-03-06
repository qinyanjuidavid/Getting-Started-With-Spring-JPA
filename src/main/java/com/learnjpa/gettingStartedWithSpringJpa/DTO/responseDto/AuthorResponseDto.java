package com.learnjpa.gettingStartedWithSpringJpa.DTO.responseDto;

import java.util.List;

import lombok.Data;

@Data
public class AuthorResponseDto {
    private Long id;
    private String name;
    private List<String> bookNames;
    private String zipCodeName;
}
