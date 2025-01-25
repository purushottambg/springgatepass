package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MembershipRequestService {

    @Autowired
    private final MembershipRepo membershipRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(MembershipRequestService.class);


    public MembershipEntity saveRequest(MembershipEntity membershipEntity){
        String firstName = membershipEntity.getFname().toLowerCase();  //Turn the program to the lower case as a good practice
        String phone = membershipEntity.getPhone();
        String userName = firstName+phone.substring(phone.length()-4);
        logger.info("Username converted to the LowerCase!");
        logger.trace("This is trace, original userID {}, converted to {}", membershipEntity.getFname()+phone.substring(phone.length()-4), userName);
        membershipEntity.setUsername(userName);   //Set Username for the new member
        membershipEntity.setPassword(passwordEncoder.encode(membershipEntity.getPassword()));   //Encode the password with the help of the password encoder by Spring Security
        logger.info("Password has been encoded");

        return membershipRepo.save(membershipEntity);
    }

    public boolean existByUserName(String userName) {
        return membershipRepo.findByUsername(userName).isPresent();
    }
}
