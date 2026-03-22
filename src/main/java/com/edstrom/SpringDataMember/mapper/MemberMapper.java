package com.edstrom.SpringDataMember.mapper;

import com.edstrom.SpringDataMember.dto.*;
import com.edstrom.SpringDataMember.entity.Address;
import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.exception.AddressNotFoundException;
import com.edstrom.SpringDataMember.repository.AddressRepository;
import com.edstrom.SpringDataMember.security.AppUser;
import com.edstrom.SpringDataMember.security.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

public final class MemberMapper {

    private MemberMapper(){}

    public static MemberPublicDto toPublicDto(Member m) {
        return new MemberPublicDto(m.getFirstName(), m.getLastName(), m.getAddress(),
                m.getEmail(), m.getPhoneNumber());
    }
    public static MemberDto toDto(Member m) {
        return new MemberDto(
                m.getId(),
                m.getFirstName(),
                m.getLastName(),
                toAddressDto(m.getAddress()),
                m.getEmail(),
                m.getPhoneNumber(),
                m.getDateOfBirth(),
                m.getAppUser().getUsername(),
                mapRoles(m.getAppUser().getRoles())
        );
    }
    static AddressDto toAddressDto(Address address) {
        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );

    }
    private static Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::name)
                .collect(Collectors.toSet());
    }
    public static void applyUpdate(Member member, MemberUpdateDto dto,
                                   PasswordEncoder passwordEncoder,
                                   AddressRepository addressRepository) {

        member.setFirstName(dto.firstName());
        member.setLastName(dto.lastName());
        member.setEmail(dto.email());
        member.setPhoneNumber(dto.phoneNumber());
        member.setDateOfBirth(dto.dateOfBirth());


        if (dto.address() != null) {
            Address address;
            if (dto.address().id() != null) {

                address = addressRepository.findById(dto.address().id())
                        .orElseThrow(() -> new AddressNotFoundException(dto.address().id()));


                address.setStreet(dto.address().street());
                address.setPostalCode(dto.address().postalCode());
                address.setCity(dto.address().city());

            } else {

                address = new Address(dto.address().street(),
                        dto.address().postalCode(),
                        dto.address().city());
                addressRepository.save(address);
            }
            member.setAddress(address);
        }

        AppUser user = member.getAppUser();
        if (dto.username() != null && !dto.username().isBlank()) {
            user.setUsername(dto.username());
        }
        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }
        if (dto.roles() != null && !dto.roles().isEmpty()) {
            user.setRoles(dto.roles().stream().map(Role::valueOf).collect(Collectors.toSet()));
        }
        member.setAppUser(user);
    }

    public static Member toEntity(MemberCreateDto dto,
                                  PasswordEncoder passwordEncoder,
                                  AddressRepository addressRepository) {
        Address address;
        if (dto.address().id() != null) {
            // Existing address from DB
            address = addressRepository.findById(dto.address().id())
                    .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + dto.address().id()));
        } else {
            // New address
            address = new Address(
                    dto.address().street(),
                    dto.address().postalCode(),
                    dto.address().city()
            );
            addressRepository.save(address);
        }
        AppUser user = new AppUser();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));

        Set<Role> roles = dto.roles().stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return new Member(
                dto.firstName(),
                dto.lastName(),
                address,
                dto.email(),
                dto.phoneNumber(),
                dto.dateOfBirth(),
                user
        );
    }
    public static void applyPatch(Member member,
                                  MemberPatchDto dto,
                                  PasswordEncoder passwordEncoder,
                                  AddressRepository addressRepository) {

        if (dto.firstName() != null) member.setFirstName(dto.firstName());
        if (dto.lastName() != null) member.setLastName(dto.lastName());
        if (dto.email() != null) member.setEmail(dto.email());
        if (dto.phoneNumber() != null) member.setPhoneNumber(dto.phoneNumber());
        if (dto.dateOfBirth() != null) member.setDateOfBirth(dto.dateOfBirth());


        AppUser user = member.getAppUser();
        if (dto.username() != null) user.setUsername(dto.username());
        if (dto.password() != null && !dto.password().isBlank())
            user.setPassword(passwordEncoder.encode(dto.password()));
        if (dto.roles() != null && !dto.roles().isEmpty()) {
            user.setRoles(dto.roles().stream().map(Role::valueOf).collect(Collectors.toSet()));
        }
        member.setAppUser(user);

        if (dto.address() != null) {
            Address address = member.getAddress();

            if (dto.address().street() != null) address.setStreet(dto.address().street());
            if (dto.address().postalCode() != null) address.setPostalCode(dto.address().postalCode());
            if (dto.address().city() != null) address.setCity(dto.address().city());

            member.setAddress(address);
        }
    }



}
