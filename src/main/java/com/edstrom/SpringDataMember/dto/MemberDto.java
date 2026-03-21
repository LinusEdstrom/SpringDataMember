package com.edstrom.SpringDataMember.dto;

import com.edstrom.SpringDataMember.entity.Address;
import com.edstrom.SpringDataMember.security.AppUser;
import com.edstrom.SpringDataMember.security.Role;

import java.time.LocalDate;
import java.util.Set;

public record MemberDto(
        Long id, String firstName, String lastName, Address address,
        String email, String phoneNumber, LocalDate dateOfBirth, String username, Set<Role> roles
        ) {
}
