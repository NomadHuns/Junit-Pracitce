package com.example.security.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 등
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // 책 등록
    @Test
    public void save_test() {
        System.out.println("save_test() 메서드 실행");
    }

    // 책 목록 조회

    // 책 한건 조회

    // 책 수정

    // 책 삭제
}
