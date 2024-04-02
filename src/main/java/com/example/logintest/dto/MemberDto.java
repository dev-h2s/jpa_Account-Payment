package com.example.logintest.dto;

import com.example.logintest.vo.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;


    private String email;

    private String pwd;

    private String name;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .pwd(pwd)
                .name(name)
                .build();
    }
}
