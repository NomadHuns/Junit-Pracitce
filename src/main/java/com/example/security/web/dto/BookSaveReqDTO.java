package com.example.security.web.dto;

import com.example.security.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
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
