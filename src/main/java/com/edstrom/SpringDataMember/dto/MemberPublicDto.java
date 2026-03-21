package com.edstrom.SpringDataMember.dto;

import com.edstrom.SpringDataMember.entity.Address;

import java.time.LocalDate;

public record MemberPublicDto(
        Long id, String firstName, String lastName, Address address, String email, String phoneNumber
) {
}
