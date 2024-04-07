package com.example.logintest.service;


import com.example.logintest.dto.MemberDto;
import com.example.logintest.repository.MemberRepository;
import com.example.logintest.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired private MemberRepository memberRepository;
    @Autowired private  PasswordEncoder passwordEncoder;



        public void memberJoin( MemberDto memberDto) throws Exception{


            Member member = Member.builder()
                    .email(memberDto.getEmail())
                    .name(memberDto.getName())
                    .pwd(passwordEncoder.encode(memberDto.getPwd())) // 패스워드 인코딩
                    .age(memberDto.getAge())
                    .roles(Collections.singletonList("ROLE_ADMIN").toString()) // 최초 가입시 USER 로 설정
                    .build();

                memberRepository.save(member);
        }
}
