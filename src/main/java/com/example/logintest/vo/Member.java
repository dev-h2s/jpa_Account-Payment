package com.example.logintest.vo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Long age;

    @Builder
    public Member(Long id, String email, String pwd, String name, Long age, PasswordEncoder passwordEncoder){
        this.id =id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
    }
}

