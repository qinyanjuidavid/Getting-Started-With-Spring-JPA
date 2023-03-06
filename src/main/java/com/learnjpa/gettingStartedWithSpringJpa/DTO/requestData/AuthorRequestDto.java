package com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String name;
    private Long zipcodeId;    
}
