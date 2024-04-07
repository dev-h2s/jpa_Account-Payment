    package com.example.logintest.controller;

    import com.example.logintest.dto.MemberDto;
    import com.example.logintest.service.MailService;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    import java.util.HashMap;
    import java.util.Map;

    @Controller
    @RequiredArgsConstructor
    public class EmailController {
        private final MailService mailService;
        private int number; // 이메일 인증 숫자를 저장하는 변수

        //이메일 유효성 검사
        // 중복 확인 및 이메일 유효성 검사
        @PostMapping("member/checkEmail")
        public ResponseEntity<HashMap<String, Object>> checkEmail(@RequestBody Map<String, String> payload) {
            String email = payload.get("email");
            HashMap<String, Object> response = new HashMap<>();

            try {
                boolean isEmailOk = mailService.emailOk(email); // 이메일 중복 확인 및 유효성 검사
                response.put("available", isEmailOk);
                response.put("message", "이메일이 확인되었습니다.");
            } catch (IllegalArgumentException e) {
                response.put("available", false);
                response.put("message", e.getMessage());
            }

            return ResponseEntity.ok(response);
        }


        // 인증 이메일 전송
        @ResponseBody
        @PostMapping("member/mailSend")
        public String MailSend(String mail){

            int number = mailService.sendMail(mail);

            String num = "" + number;

            return num;
        }

        // 인증번호 일치여부 확인
        @GetMapping("/mailCheck")
        public ResponseEntity<?> mailCheck(@RequestParam String userNumber) {

            boolean isMatch = userNumber.equals(String.valueOf(number));

            return ResponseEntity.ok(isMatch);
        }
    }
