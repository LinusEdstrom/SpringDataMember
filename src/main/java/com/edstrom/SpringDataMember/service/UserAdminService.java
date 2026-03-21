package com.edstrom.SpringDataMember.service;

import com.edstrom.SpringDataMember.dto.MemberDto;
import com.edstrom.SpringDataMember.exception.MemberNotFoundException;
import com.edstrom.SpringDataMember.mapper.MemberMapper;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserAdminService {

    private final MemberRepository memberRepository;

    public UserAdminService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Transactional(readOnly = true)
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberMapper::toDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public MemberDto findById(Long id) {
        return memberRepository.findById(id)
                .map(MemberMapper::toDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }


}
