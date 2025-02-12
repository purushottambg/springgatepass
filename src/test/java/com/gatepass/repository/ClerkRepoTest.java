package com.gatepass.repository;

import com.gatepass.models.MembershipEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ClerkRepoTest {

    @Autowired
    private MembershipRepo membershipRepo;
    private MembershipEntity membershipEntity;

    @BeforeEach
    public void buildMemberWithRequest(){
        membershipEntity = MembershipEntity.builder()
                .appid(Long.valueOf(123))
                .username("pallavi7497")
                .fname("Pallavi")
                .sname("Purushottam")
                .lname("Gutthe")
                .designation("Doctor")
                .phone("7666947497")
                .email("dr.pallavi11@gmail.com")
                .password("PallaviLovesPurushottam")
                .build();
    }

    @Test
    void findByUserName() {
        //arrange => give
        membershipRepo.save(membershipEntity);

        //act => when
        boolean foundEntity = membershipRepo.existsById(Long.valueOf(123));
        //assert => then

        Assertions.assertEquals(foundEntity, true);
    }
}