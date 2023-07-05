package com.example.security.web.dto;

import com.example.security.domain.Book;
import lombok.*;

@Getter @NoArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;

    public BookResponseDTO toDTO(Book bookPS) {
        this.id = bookPS.getId();
        this.title = bookPS.getTitle();
        this.author = bookPS.getAuthor();
        return this;
    }
}
