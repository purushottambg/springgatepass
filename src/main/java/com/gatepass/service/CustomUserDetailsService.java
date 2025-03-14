package com.gatepass.service;

import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StaffRepo staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from DB. Assuming "Staff" is your user entity for now.
        StaffEntity staff = staffRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert your "Staff" object to UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(staff.getUsername())
                .password(staff.getPassword())  // Password should already be encoded in DB.
                .roles(staff.getRole())  // Assuming role is stored in "Staff" entity.
                .build();
    }
}
