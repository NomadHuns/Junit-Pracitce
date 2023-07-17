package com.example.security.web.dto;

import com.example.security.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BookSaveReqDTO {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }

}
