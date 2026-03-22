package com.edstrom.SpringDataMember.controller;

import com.edstrom.SpringDataMember.dto.MemberCreateDto;
import com.edstrom.SpringDataMember.dto.MemberDto;
import com.edstrom.SpringDataMember.dto.MemberPatchDto;
import com.edstrom.SpringDataMember.dto.MemberUpdateDto;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import com.edstrom.SpringDataMember.service.UserAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public MemberDto get(@PathVariable Long id)
    {return userAdminService.findById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MemberDto update(@PathVariable Long id, @RequestBody @Valid MemberUpdateDto dto){
        return userAdminService.update(id, dto);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MemberDto> create(@RequestBody @Valid MemberCreateDto dto) {
        MemberDto createdAndSaved = userAdminService.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAndSaved.id()).toUri();

        return ResponseEntity
                .created(location)
                .body(createdAndSaved);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        userAdminService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MemberDto> patchMember(@PathVariable Long id,
                                                 @RequestBody MemberPatchDto dto ){
        MemberDto patchedMember = userAdminService.patch(id, dto);
        return ResponseEntity.ok(patchedMember);
    }











}
