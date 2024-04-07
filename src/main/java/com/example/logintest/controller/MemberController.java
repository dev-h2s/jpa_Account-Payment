package com.example.logintest.controller;

import com.example.logintest.dto.MemberDto;
import com.example.logintest.repository.MemberRepository;
import com.example.logintest.security.JwtTokenProvider;
import com.example.logintest.service.MemberService;
import com.example.logintest.vo.Member;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @GetMapping("/main")
    public String main() {

        return "redirect:/main.html";
    }

    @GetMapping("member/login")
    public String login() {
        return "login";
    }

    @GetMapping("/member/sign")
    public String getSignPage() {
        return "sign";
    }

    @PostMapping(value = "/member/sign")
    public ResponseEntity<String> signMember(@RequestBody @Valid MemberDto memberDto) {
        try {
            memberService.memberJoin(memberDto);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        try {
            Member member = memberRepository.findByEmail(user.get("email"))
                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
            if (!passwordEncoder.matches(user.get("pwd"), member.getPwd())) {
                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
            }
            String token = jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
            return ResponseEntity.ok(Map.of("success", true, "token", token,  "email", member.getEmail())); // 성공 시 토큰 반환
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage())); // 실패 시 메시지 반환
        }
    }

}


// 로그인
//    @PostMapping("/login")
//    public String login(@RequestBody Map<String, String> user) {
//        Member member = memberRepository.findByEmail(user.get("email"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//        if (!passwordEncoder.matches(user.get("password"), member.getPwd())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        return jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
//    }