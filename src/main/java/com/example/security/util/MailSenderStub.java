package com.example.security.util;

import org.springframework.stereotype.Component;

// 가짜 메일링 시스템
@Component
public class MailSenderStub implements MailSender{

    @Override
    public boolean send() {
        return true;
    }
}
