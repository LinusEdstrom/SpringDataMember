package com.edstrom.SpringDataMember.service;


import com.edstrom.SpringDataMember.entity.Address;
import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.repository.AddressRepository;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    public MemberServiceImpl(MemberRepository memberRepository, AddressRepository addressRepository){
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
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
                LocalDate.of(2001, 3, 23)
        );
        Member member2 = new Member(
                "Julio",
                "Ingrosso",
                address1,
                "JulioIng@hotmail.com",
                "0736117881",
                LocalDate.of(2003, 8, 10)
        );
        Member member3 = new Member(
                "Roberto",
                "Ingrosso",
                address1,
                "RobertoIng@hotmail.com",
                "0714403411",
                LocalDate.of(2006, 1, 4)
        );
        Member member4 = new Member(
                "Svissilina",
                "Membriania",
                address2,
                "SvirlySvissi@gotchamail.com",
                "0703990100",
                LocalDate.of(2005, 11, 25)
        );
        Member member5 = new Member(
                "Tristessilina",
                "Membriania",
                address2,
                "Boringalina@gotchamail.com",
                "0707221186",
                LocalDate.of(2006, 7, 2)
        );
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
    }
    }




