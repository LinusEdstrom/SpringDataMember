package com.edstrom.SpringDataMember.service;

import com.edstrom.SpringDataMember.dto.MemberCreateDto;
import com.edstrom.SpringDataMember.dto.MemberDto;
import com.edstrom.SpringDataMember.dto.MemberPatchDto;
import com.edstrom.SpringDataMember.dto.MemberUpdateDto;
import com.edstrom.SpringDataMember.entity.Address;
import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.exception.AddressNotFoundException;
import com.edstrom.SpringDataMember.exception.MemberNotFoundException;
import com.edstrom.SpringDataMember.mapper.MemberMapper;
import com.edstrom.SpringDataMember.repository.AddressRepository;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import com.edstrom.SpringDataMember.security.AppUser;
import com.edstrom.SpringDataMember.security.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAdminService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    public UserAdminService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder,
                            AddressRepository addressRepository){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
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
    @Transactional
    public MemberDto update(Long id, MemberUpdateDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

        MemberMapper.applyUpdate(member, dto,
                passwordEncoder, addressRepository);
        return MemberMapper.toDto(memberRepository.save(member));
    }

    @Transactional
    public MemberDto create(MemberCreateDto dto) {
        Member entityMember = MemberMapper.toEntity(dto, passwordEncoder, addressRepository);
        Member saved = memberRepository.save(entityMember);
        return MemberMapper.toDto(saved);
    }
    @Transactional
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException(id);
        }
        memberRepository.deleteById(id);
    }
    @Transactional
    public MemberDto patch(Long id, MemberPatchDto dto) {
    Member member = memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(id));

    MemberMapper.applyPatch(member, dto, passwordEncoder, addressRepository);

    Member updated = memberRepository.save(member);
    return MemberMapper.toDto(updated);
    }
}
