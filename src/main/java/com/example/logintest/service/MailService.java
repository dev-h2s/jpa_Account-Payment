package com.example.logintest.service;


import com.example.logintest.dto.MemberDto;
import com.example.logintest.repository.MemberRepository;
import com.example.logintest.vo.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "shsdev96@gmail.com";
    private static int number;




    @Autowired private MemberRepository memberRepository;


        public boolean emailOk(String email) {
            // 중복 이메일 검사
            boolean exists = memberRepository.existsByEmail(email);
            if (exists) {
                throw new IllegalArgumentException("중복된 이메일입니다.");
            }
            if (!MemberUtil.isValidEmail(email)) {
                throw new IllegalArgumentException("잘못된 이메일 형식입니다.");
            }
            return true; // 이메일이 유효하고 중복되지 않은 경우 true 반환
        }


    // 랜덤으로 숫자 생성
    public static void createNumber() {
        number = (int)(Math.random() * (90000)) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String mail) {
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail) {
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);

        return number;
    }
}