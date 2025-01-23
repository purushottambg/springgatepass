package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipRequestService {

    @Autowired
    private MembershipRepo membershipRepo;
    Logger logger = LoggerFactory.getLogger(MembershipRequestService.class);
    public MembershipEntity saveRequest(MembershipEntity membershipEntity){
        String firstName = membershipEntity.getFname().toLowerCase();  //Turn the program to the lower case as a good practice
        String phone = membershipEntity.getPhone();
        String userName = firstName+phone.substring(phone.length()-4);
        logger.info("Username converted to the LowerCase!");
        logger.trace("This is trace, original userID {}, converted to {}", membershipEntity.getFname()+phone.substring(phone.length()-4), userName);
        membershipEntity.setUsername(userName);   //Set Username for the new member
        return membershipRepo.save(membershipEntity);
    }

    public boolean existByUserName(String userName) {
        return membershipRepo.findByUsername(userName).isPresent();
    }
}
