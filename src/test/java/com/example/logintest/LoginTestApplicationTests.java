package com.example.logintest;

import com.example.logintest.repository.MemberRepository;
import com.example.logintest.vo.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class LoginTestApplicationTests {
    @Autowired
    private MemberRepository memberRepository;


    @Test
    void sign() {
        // 멤버 저장
        Member member = Member.builder()
                .name("비겁자 김진")
                .pwd("1234")
                .email("@naver.com")
                .build();
        memberRepository.save(member);

        // 저장한 멤버 아이디로 검색
        Member findMember = memberRepository.findById(member.getId()).orElse(null);
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }
}


