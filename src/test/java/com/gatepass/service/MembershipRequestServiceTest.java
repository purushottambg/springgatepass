package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MembershipRequestServiceTest {

    @InjectMocks
    MembershipRequestService membershipRequestService;



    @Test
    void getDynamicUsername() {
        MembershipEntity membershipEntity = MembershipEntity.builder()
                .fname("Pallavi").phone("7666947497")
        .build();
         membershipEntity.setFname("Pallavi");
         membershipEntity.setPhone("7666947497");
        String username = membershipRequestService.getDynamicUsername(membershipEntity);

        Assertions.assertThat(username).isNotBlank();
        Assertions.assertThat(username).isNotNull();
        Assertions.assertThat(username).isEqualTo("pallavi7497");
    }
}