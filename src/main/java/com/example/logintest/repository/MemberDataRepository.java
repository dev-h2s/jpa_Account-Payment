package com.example.logintest.repository;

import com.example.logintest.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDataRepository extends MemberRepository, JpaRepository<Member, Long> {

    @Override
    Optional<Member> findByName(String name);
}
