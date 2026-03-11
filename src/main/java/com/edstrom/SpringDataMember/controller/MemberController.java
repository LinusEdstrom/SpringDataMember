package com.edstrom.SpringDataMember.controller;


import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping
    public List<Member> list(){return service.findAll}

}
