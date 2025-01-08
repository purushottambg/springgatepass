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
        return membershipRepo.save(membershipRequest);
    }
}
