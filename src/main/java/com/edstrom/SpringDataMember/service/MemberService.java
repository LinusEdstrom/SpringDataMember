package com.edstrom.SpringDataMember.service;


import com.edstrom.SpringDataMember.dto.MemberDto;
import com.edstrom.SpringDataMember.dto.MemberPublicDto;
import com.edstrom.SpringDataMember.dto.MemberUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {

void createSampleData();

List<MemberPublicDto> findAll();
MemberPublicDto findById(Long id);
MemberDto update(Long id, MemberUpdateDto dto);
}
