package com.gatepass.auth;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  //This is the global variable to access all the values in security context

        if (authentication == null && !authentication.isAuthenticated()) {
            return Optional .of("Purushottam Gutthe");
        }

        return Optional.of(authentication.getName());
    }
}

