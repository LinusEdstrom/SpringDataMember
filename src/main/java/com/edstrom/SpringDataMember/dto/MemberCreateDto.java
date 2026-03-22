package com.edstrom.SpringDataMember.dto;

import com.edstrom.SpringDataMember.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public record MemberCreateDto(

        @NotBlank @Size(min = 2, max = 20) String firstName,
        @NotBlank @Size(min = 2, max = 20) String lastName,
        @NotNull @Valid AddressDto address,
        @NotBlank @Email @Size(max = 50) String email,
        @Size(max = 20) String phoneNumber,
        LocalDate dateOfBirth,
        @NotBlank @Size(min = 2, max = 100) String username,
        @NotBlank @Size(min = 8, max = 60) String password,
        Set<String> roles
) {
}
