package com.edstrom.SpringDataMember.service;

import com.edstrom.SpringDataMember.entity.Member;
import com.edstrom.SpringDataMember.repository.MemberRepository;
import com.edstrom.SpringDataMember.security.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public AppUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByAppUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        AppUser appUser = member.getAppUser();

        return appUser;
    }
}
