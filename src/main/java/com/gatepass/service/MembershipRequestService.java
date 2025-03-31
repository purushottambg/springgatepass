package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class MembershipRequestService implements UserDetailsService {

    private final MembershipRepo membershipRepo;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(MembershipRequestService.class);

    @Transactional
    public MembershipEntity saveRequest(MembershipEntity membershipEntity) {
        membershipEntity.setUsername(getDynamicUsername(membershipEntity));
        membershipEntity.setPassword(passwordEncoder.encode(membershipEntity.getPassword()));
        logger.info("Password encoded successfully");
        return membershipRepo.save(membershipEntity);
    }

    public String getDynamicUsername(MembershipEntity membershipEntity) {
        String firstName = membershipEntity.getFname().toLowerCase();
        String phone = membershipEntity.getPhone();
        return firstName + phone.substring(phone.length() - 4);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("MemberShipRequest: Inside loadUserByUsername for user: {}", username);
        return membershipRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " does not exist!"));
    }

    public Optional<MembershipEntity> findByID(Long appid) {
        return membershipRepo.findById(appid);
    }

    public boolean existsByUsername(String userName) {
        return membershipRepo.findByUsername(userName).isPresent();
    }
}