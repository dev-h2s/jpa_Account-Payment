package com.example.logintest.service;


import com.example.logintest.dto.MemberDto;
import com.example.logintest.repository.MemberRepository;
import com.example.logintest.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired private MemberRepository memberRepository;


    public void memberJoin(MemberDto memberDto) throws Exception{
        // 중복 아이디 검사
        boolean exists = memberRepository.existsByEmail(memberDto.getEmail());
        if (exists) {
            throw new IllegalArgumentException("중복된 이메일 입니다");
        }
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .pwd(memberDto.getPwd())
                .build();

            memberRepository.save(member);
    }
}