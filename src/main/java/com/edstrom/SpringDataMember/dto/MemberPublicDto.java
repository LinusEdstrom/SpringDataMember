package com.edstrom.SpringDataMember.dto;

import com.edstrom.SpringDataMember.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record MemberPublicDto(

        @NotBlank @Size(min = 2, max = 20) String firstName,
        @NotBlank @Size(min = 2, max = 20)String lastName,
        @NotBlank Address address,
        @NotBlank @Email @Size(max = 50) String email,
        @Size (max = 20) String phoneNumber
) {
}
