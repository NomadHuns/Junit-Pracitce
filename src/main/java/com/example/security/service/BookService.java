package com.example.security.service;

import com.example.security.domain.Book;
import com.example.security.domain.BookRepository;
import com.example.security.util.MailSender;
import com.example.security.web.dto.response.BookListResponseDTO;
import com.example.security.web.dto.response.BookResponseDTO;
import com.example.security.web.dto.request.BookSaveReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    // 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDTO 책등록하기(BookSaveReqDTO requestDTO) {
        Book bookPS = bookRepository.save(requestDTO.toEntity());
        if (bookPS != null) {
            if (!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }
        return bookPS.toDTO();
    }

    ;

    // 책 목록 조회
    public BookListResponseDTO 책목록보기() {
        List<BookResponseDTO> bookResponseDTOList =
                bookRepository.findAll().stream()
                .map(Book::toDTO)
                .collect(Collectors.toList());

        return new BookListResponseDTO(bookResponseDTOList);
    }

    // 책 한건 조회
    public BookResponseDTO 책한건보기(Long id) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) {
            Book bookPS = bookOP.get();
            return bookPS.toDTO();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    // 책 삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제(Long id) {
        bookRepository.deleteById(id);
    }

    // 책 수정
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDTO 책수정(Long id, BookSaveReqDTO reqDTO) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) {
            Book bookPS = bookOP.get();
            BookResponseDTO responseDTO = bookPS.update(reqDTO.getTitle(), reqDTO.getAuthor());
            return responseDTO;
            // 메서드 종료시에 더티체킹(flush)으로 업데이트됨
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
}
