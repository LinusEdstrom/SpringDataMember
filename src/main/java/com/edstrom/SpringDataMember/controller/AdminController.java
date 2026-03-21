package com.edstrom.SpringDataMember.controller;

import com.edstrom.SpringDataMember.dto.MemberDto;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import com.edstrom.SpringDataMember.service.UserAdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/members")

public class AdminController {

    private final UserAdminService userAdminService;


    public AdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;

    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<MemberDto> listAll() {
        return userAdminService.findAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MemberDto get(@PathVariable Long id) {return userAdminService.findById(id);}





}
