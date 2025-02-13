package com.gatepass.repository;

import com.gatepass.models.MembershipEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class MembershipRepoTest {

    @Autowired
    private MembershipRepo membershipRepo;
    private MembershipEntity membershipEntity;

    @BeforeEach
    public void setUp(){
        membershipEntity = MembershipEntity.builder()
                .appid(Long.valueOf(234))
                .username("pallavi7497")
                .fname("Pallavi")
                .sname("Purushottam")
                .lname("Gutthe")
                .dept(Long.valueOf(2))
                .designation("Doctor")
                .phone("7666947497")
                .email("pallavidarade@gmail.com")
                .password("PallviLovesPurushottam7411")
                .build();
    }
    @Test
    void findByUsername() {
        //arrange, Given
        membershipRepo.save(membershipEntity);
        //act, when
        List<MembershipEntity> members = membershipRepo.findByAppid(membershipEntity.getAppid());
        //assert, then
        Assertions.assertThat(members).isNotNull();
    }
}