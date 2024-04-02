package com.example.logintest.repository;

import com.example.logintest.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);
    List<Member> findAll();

    // 아이디 중복검사
    boolean existsByEmail(String email);

}
