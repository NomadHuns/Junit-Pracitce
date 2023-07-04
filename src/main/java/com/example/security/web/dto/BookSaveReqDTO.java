package com.example.security.web.dto;

import com.example.security.domain.Book;
import lombok.Setter;

@Setter
public class BookSaveReqDTO {
    private String titie;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(titie)
                .author(author)
                .build();
    }

}
