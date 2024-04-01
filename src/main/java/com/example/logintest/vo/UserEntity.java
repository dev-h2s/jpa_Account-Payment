package com.example.logintest.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "employee_tb")
public class UserEntity {
    @Id
    private Long id;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "pwd")
    private String pwd;

}
