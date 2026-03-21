package com.edstrom.SpringDataMember.service;


import com.edstrom.SpringDataMember.dto.MemberPublicDto;
import com.edstrom.SpringDataMember.entity.Address;
import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.exception.MemberNotFoundException;
import com.edstrom.SpringDataMember.mapper.MemberMapper;
import com.edstrom.SpringDataMember.repository.AddressRepository;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import com.edstrom.SpringDataMember.security.AppUser;
import com.edstrom.SpringDataMember.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional(readOnly = true)
    public List<MemberPublicDto> findAll(){
        return memberRepository.findAll().stream()
                .map(MemberMapper::toPublicDto)
                .toList();
    }
    @Override
    @Transactional(readOnly = true)
    public MemberPublicDto findById(Long id){
        return memberRepository.findById(id) //(Optional members blir streamade)
                .map(MemberMapper::toPublicDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }


    @Override
    @Transactional
    public void createSampleData(){
        Address address1 = new Address("CoalWatcherAlley", "907 42", "Umeå");
        Address address2 = new Address("Village St 1", "930 10", "Hawkground");

        addressRepository.save(address1);
        addressRepository.save(address2);

         Member member1 = new Member(
                "Bianca",
                "Ingrosso",
                address1,
                "BiancaIng@hotmail.com",
                "0735563440",
                LocalDate.of(2001, 3, 23),
                 new AppUser(
                 "bianca",
                 passwordEncoder.encode("password"),
                 Set.of(Role.ADMIN)
        ));

         Member member2 = new Member(
                "Julio",
                "Ingrosso",
                address1,
                "JulioIng@hotmail.com",
                "0736117881",
                LocalDate.of(2003, 8, 10),
                 new AppUser(
                 "julio",
                 passwordEncoder.encode("juliosSecretpassword"),
                 Set.of(Role.USER)
        ));

         Member member3 = new Member(
                "Roberto",
                "Ingrosso",
                address1,
                "RobertoIng@hotmail.com",
                "0714403411",
                LocalDate.of(2006, 1, 4),
                 new AppUser(
                 "roberto",
                 passwordEncoder.encode("Robertosobviouspassword"),
                 Set.of(Role.USER)
        ));

        Member member4 = new Member(
                "Svissilina",
                "Membriania",
                address2,
                "SvirlySvissi@gotchamail.com",
                "0703990100",
                LocalDate.of(2005, 11, 25),
                new AppUser(
                "svissilina",
                passwordEncoder.encode("marangsvissilina"),
                Set.of(Role.USER)
        ));

         Member member5 = new Member(
                "Tristessilina",
                "Membriania",
                address2,
                "Boringalina@gotchamail.com",
                "0707221186",
                LocalDate.of(2006, 7, 2),
                 new AppUser(
                 "tristessilina",
                 passwordEncoder.encode("Boringilina"),
                 Set.of(Role.USER)
        ));
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
    }
    }




