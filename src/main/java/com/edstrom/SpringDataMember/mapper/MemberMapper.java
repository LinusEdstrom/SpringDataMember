package com.edstrom.SpringDataMember.mapper;

import com.edstrom.SpringDataMember.dto.MemberDto;
import com.edstrom.SpringDataMember.dto.MemberPublicDto;
import com.edstrom.SpringDataMember.entity.Member;

public final class MemberMapper {

    private MemberMapper(){}

    public static MemberPublicDto toPublicDto(Member m) {
        return new MemberPublicDto(m.getId(), m.getFirstName(), m.getLastName(), m.getAddress(),
                m.getEmail(), m.getPhoneNumber());
    }
    public static MemberDto toDto(Member m) {
        return new MemberDto(m.getId(), m.getFirstName(), m.getLastName(), m.getAddress(),
                m.getEmail(), m.getPhoneNumber(), m.getDateOfBirth(), m.getAppUser().getUsername(),
                m.getAppUser().getRoles());
    }

}
