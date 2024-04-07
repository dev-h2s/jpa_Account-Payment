package com.example.logintest.dto;

import com.example.logintest.vo.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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


    @NotBlank(message =  "이메일은 필수 입력 항목입니다.")
    private String email;

    @Size(min = 8, max = 10, message = "패스워드 길이는 8~10자여야 합니다.")
    @NotBlank(message =  "패스워드는 필수 입력 항목입니다.")
    private String pwd;

    @NotBlank(message =  "이름은 필수 입력 항목입니다.")
    private String name;

    private Long age;

    private String roles;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .pwd(pwd)
                .name(name)
                .age(age)
                .roles(roles)
                .build();
    }
}
