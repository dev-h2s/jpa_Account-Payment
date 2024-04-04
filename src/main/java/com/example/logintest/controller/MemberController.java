package com.example.logintest.controller;

import com.example.logintest.dto.MemberDto;
import com.example.logintest.repository.MemberRepository;
import com.example.logintest.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
    @Autowired  private MemberRepository memberRepository;
    @Autowired private MemberService memberService;



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

}
