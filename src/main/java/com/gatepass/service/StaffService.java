package com.gatepass.service;

import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    private final StaffRepo staffRepo;

    public Boolean existByUserName(String userName){
        return staffRepo.findByUsername(userName).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return staffRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user with "+username+" found in the database!"));
    }
}
