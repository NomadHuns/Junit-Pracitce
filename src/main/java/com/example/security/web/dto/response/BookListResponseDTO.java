package com.example.security.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BookListResponseDTO {
    List<BookResponseDTO> items;

    @Builder
    public BookListResponseDTO(List<BookResponseDTO> bookList) {
        this.items = bookList;
    }
}
