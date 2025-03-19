package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Lazy
    private final StaffService staffService;
    private final HODService hodService;
    private final ClerkService clerkService;
    private final PrincipalService principalService;
    private final JwtService jwtService;
    private final MembershipRequestService membershipRequestService;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * Identifies the user type and fetches user details accordingly.
     */
    public UserDetails getUserDetails(String username) {
        if (staffService.existsByUsername(username)) {
            return staffService.loadUserByUsername(username);
//        } else if (hodService.existByUserName(username)) {
//            return hodService.loadUserByUsername(username);
//        } else if (clerkService.existByUserName(username)) {
//            return clerkService.loadUserByUsername(username);
//        } else if (principalService.existByUserName(username)) {
//            return principalService.loadUserByUsername(username);
        } else if (membershipRequestService.existsByUsername(username)) {
            return membershipRequestService.loadUserByUsername(username);
        }
        return null;
    }
}
