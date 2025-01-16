package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipRequestService {

    @Autowired
    private MembershipRepo membershipRepo;

    public MembershipEntity saveRequest(MembershipEntity membershipEntity){
        String firstName = membershipEntity.getFname().toLowerCase();  //Turn the program to the lower case as a good practice
        String phone = membershipEntity.getPhone();
        String userName = firstName+phone.substring(phone.length()-4);
        membershipEntity.setUsername(userName);   //Set Username for the new member
        return membershipRepo.save(membershipEntity);
    }

    public boolean existByUserName(String userName) {
        return membershipRepo.findByUsername(userName).isPresent();
    }
}
