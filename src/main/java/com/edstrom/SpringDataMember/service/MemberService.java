package com.edstrom.SpringDataMember.service;


import com.edstrom.SpringDataMember.dto.MemberPublicDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {

void createSampleData();

List<MemberPublicDto> findAll();
MemberPublicDto findById(Long id);

}
