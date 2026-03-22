package com.edstrom.SpringDataMember.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public record MemberPatchDto(
        String firstName,
        String lastName,
        AddressPatchDto address,
        String email,
        String phoneNumber,
        LocalDate dateOfBirth,
        String username,
        String password,
        Set<String> roles

) {
}
