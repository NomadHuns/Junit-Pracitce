package com.example.security.web;

import com.example.security.service.BookService;
import com.example.security.web.dto.request.BookSaveReqDTO;
import com.example.security.web.dto.response.BookResponseDTO;
import com.example.security.web.dto.response.CMRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDTO requestDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            throw new RuntimeException(errorMap.toString());
        }

        BookResponseDTO bookResponseDTO = bookService.책등록하기(requestDTO);
        return new ResponseEntity<>(CMRespDTO.builder().code(1).msg("책 저장 성공").body(bookResponseDTO).build(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getBookList() {
        return null;
    }

    public ResponseEntity<?> getBookOne() {
        return null;
    }

    public ResponseEntity<?> deleteBook() {
        return null;
    }

    public ResponseEntity<?> updateBook() {
        return null;
    }
}
