package com.example.security.web;

import com.example.security.service.BookService;
import com.example.security.web.dto.request.BookSaveReqDTO;
import com.example.security.web.dto.response.BookListResponseDTO;
import com.example.security.web.dto.response.BookResponseDTO;
import com.example.security.web.dto.response.CMRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

        BookResponseDTO responseDTO = bookService.책등록하기(requestDTO);
        return new ResponseEntity<>(CMRespDTO.builder().code(1).msg("책 저장 성공").body(responseDTO).build(), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/book")
    public ResponseEntity<?> getBookList() {
        BookListResponseDTO responseDTO = bookService.책목록보기();
        return new ResponseEntity<>(CMRespDTO.builder().code(1).msg("책 목록보기 성공").body(responseDTO).build(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/book/{id}")
    public ResponseEntity<?> getBookOne(@PathVariable Long id) {
        BookResponseDTO responseDTO = bookService.책한건보기(id);
        return new ResponseEntity<>(CMRespDTO.builder().code(1).msg("책 한건 보기 성공").body(responseDTO).build(), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.책삭제(id);
        return new ResponseEntity<>(CMRespDTO.builder().code(1).msg("책 한건 보기 성공").body(null).build(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateBook() {
        return null;
    }
}
