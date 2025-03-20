package com.gatepass.service;


import com.gatepass.models.MembershipEntity;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.MembershipRepo;
import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class CustomUserDetailsService implements UserDetailsService {

    private final StaffRepo staffRepository;
    private final MembershipRepo membershipRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(staffRepository.existsByUsername(username)){
            StaffEntity staff = staffRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("could not load the user from staff repository"));
            return modelMapper.map(staff, UserDetails.class);
        }else if(membershipRepo.existsByUsername(username)){
            MembershipEntity membershipEntity = membershipRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("could not load the user from staff repository"));
            return modelMapper.map(membershipEntity, UserDetails.class);

        }else throw new UsernameNotFoundException("CustomUserDetailsService: we couldn't find the user with: "+ username+" username");

    }
}
