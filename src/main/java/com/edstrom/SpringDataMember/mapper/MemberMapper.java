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
                                   BCryptPasswordEncoder passwordEncoder,
                                   AddressRepository addressRepository) {
       member.setFirstName(dto.firstName());
        member.setLastName(dto.lastName());
        member.setEmail(dto.email());
        member.setPhoneNumber(dto.phoneNumber());
        member.setDateOfBirth(dto.dateOfBirth());

        Address address;
        if (dto.address().id() != null) {
            address = addressRepository.findById(dto.address().id())
                    .orElseThrow(() -> new IllegalArgumentException("Address not found"));
        } else {
            address = new Address(dto.address().street(), dto.address().postalCode(), dto.address().city());
            addressRepository.save(address);
        }
        member.setAddress(address);

        // Handle user
        AppUser user = member.getAppUser();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(dto.roles().stream().map(Role::valueOf).collect(Collectors.toSet()));
        member.setAppUser(user);
    }

    public static Member toEntity(MemberCreateDto dto,
                                  BCryptPasswordEncoder passwordEncoder,
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
                                  BCryptPasswordEncoder passwordEncoder,
                                  AddressRepository addressRepository) {

        if (dto.firstName() != null) member.setFirstName(dto.firstName());
        if (dto.lastName() != null) member.setLastName(dto.lastName());
        if (dto.email() != null) member.setEmail(dto.email());
        if (dto.phoneNumber() != null) member.setPhoneNumber(dto.phoneNumber());
        if (dto.dateOfBirth() != null) member.setDateOfBirth(dto.dateOfBirth());

        if (dto.username() != null) member.getAppUser().setUsername(dto.username());
        if (dto.password() != null)
            member.getAppUser().setPassword(passwordEncoder.encode(dto.password()));
        if (dto.roles() != null) {
            Set<Role> roles = dto.roles().stream()
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            member.getAppUser().setRoles(roles);
        }

        if (dto.address() != null) {
            Address address;
            if (dto.address().id() != null) {
                // Fetch existing
                address = addressRepository.findById(dto.address().id())
                        .orElseThrow(() -> new AddressNotFoundException(dto.address().id()));
            } else {
                // Create new
                address = new Address(dto.address().street(),
                        dto.address().postalCode(),
                        dto.address().city());
                addressRepository.save(address);
            }
            member.setAddress(address);
        }
    }


}
