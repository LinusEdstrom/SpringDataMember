package com.edstrom.SpringDataMember.repository;

import com.edstrom.SpringDataMember.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
