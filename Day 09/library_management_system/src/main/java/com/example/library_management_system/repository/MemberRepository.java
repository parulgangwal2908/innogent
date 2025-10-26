package com.example.library_management_system.repository;

import com.example.library_management_system.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
