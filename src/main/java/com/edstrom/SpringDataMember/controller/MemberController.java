package com.edstrom.SpringDataMember.controller;


import com.edstrom.SpringDataMember.dto.MemberPublicDto;
import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping
    public List<MemberPublicDto> list(){return memberService.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<MemberPublicDto> get(@PathVariable Long id) {
        MemberPublicDto memberSearched = memberService.findById(id);

        {
            return ResponseEntity.ok(memberSearched);
        }
    }
    /*@PutMapping("/{id}")
    public ResponseEntity<MemberPublicDto> update(@PathVariable Long id, @RequestBody @Valid MemberPublicDto memberPublicDto){

        MemberPublicDto memberUpdated = memberService.update(id, memberPublicDto);

        return ResponseEntity.ok(memberUpdated);
    }

     */




}
