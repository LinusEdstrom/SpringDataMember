package com.edstrom.SpringDataMember.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public Long getLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = (AppUser) auth.getPrincipal();

        return appUser.getMember().getId();
    }
}
