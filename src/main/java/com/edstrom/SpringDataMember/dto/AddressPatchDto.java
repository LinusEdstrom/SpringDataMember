package com.edstrom.SpringDataMember.dto;

public record AddressPatchDto(
        Long id,
        String street,
        String postalCode,
        String city
) {
}
