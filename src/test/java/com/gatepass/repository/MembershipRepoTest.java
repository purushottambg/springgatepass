package com.gatepass.repository;

import com.gatepass.models.MembershipEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.sql.Connection;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@EntityScan(basePackages = "com.gatepass.models")
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.properties")

class MembershipRepoTest {

    @Autowired
    private MembershipRepo membershipRepo;
    private MembershipEntity membershipEntity;
    @Autowired
    private DataSource dataSource;
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
    void testH2Connection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "H2 connection should be available");
            System.out.println("Connected to H2 successfully!");
        }
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

    @Test
    void testDatabaseConnection() throws SQLException {
        System.out.println("Using DB: " + dataSource.getConnection().getMetaData().getURL());
    }


}