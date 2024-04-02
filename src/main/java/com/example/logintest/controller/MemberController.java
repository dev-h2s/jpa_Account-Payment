package com.example.logintest.controller;

import com.example.logintest.dto.MemberDto;
import com.example.logintest.repository.MemberRepository;
import com.example.logintest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired  private MemberRepository memberRepository;
    @Autowired private MemberService memberService;
    @PostMapping(value = "member/sign")
    public ResponseEntity<String> signMember(@RequestBody MemberDto memberDto) throws Exception {
    try {
        memberService.memberJoin(memberDto);
        return ResponseEntity.ok("회원가입 완료");
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("회원가입중 오류 발생 " + e.getMessage());
    }
    }
}
