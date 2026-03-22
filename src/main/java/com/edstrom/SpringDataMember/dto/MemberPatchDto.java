package com.edstrom.SpringDataMember.dto;

import org.springframework.lang.Nullable;
import java.time.LocalDate;
import java.util.Set;

public record MemberPatchDto(
        @Nullable String firstName,
        @Nullable String lastName,
        @Nullable AddressPatchDto address,
        @Nullable String email,
        @Nullable String phoneNumber,
        @Nullable LocalDate dateOfBirth,
        @Nullable String username,
        @Nullable String password,
        @Nullable Set<String> roles
) {}
