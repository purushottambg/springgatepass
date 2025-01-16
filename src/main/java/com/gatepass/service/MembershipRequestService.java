package com.gatepass.service;

import com.gatepass.models.MembershipRequest;
import com.gatepass.repository.MembershipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipRequestService {

    @Autowired
    private MembershipRepo membershipRepo;

    public MembershipRequest saveRequest(MembershipRequest membershipRequest){
        String firstName = membershipRequest.getFname().toLowerCase();  //Turn the program to the lower case as a good practice
        String phone = membershipRequest.getPhone();
        String userName = firstName+phone.substring(phone.length()-4);
        membershipRequest.setUsername(userName);   //Set Username for the new member
        return membershipRepo.save(membershipRequest);
    }

    public boolean existByUserName(String userName) {
        return membershipRepo.findByUsername(userName).isPresent();
    }
}
