package com.gatepass.repository;

import com.gatepass.models.DepartmentEntity;
import com.gatepass.models.StaffEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StaffRepoTest {

    @Autowired
    private StaffRepo staffRepo;
    private StaffEntity staffEntity;
    private DepartmentEntity departmentEntity;

    @BeforeEach
    void buildStaff(){
        departmentEntity = DepartmentEntity.builder()
                .dptid(Long.valueOf(4))
                .deptname("Pediatrics")
                .build();
        staffEntity= StaffEntity.builder()
                .staffid(Long.valueOf(25))
                .username("pallavi7497")
                .fname("Pallavi")
                .sname("Purushottam")
                .lname("Gutthe")
                .designation("Doctor")
                .phone("7666947497")
                .departmentEntity( departmentEntity)
                .email("dr.pallavi11@gmail.com")
                .password("PallaviLovesPurushottam")
                .build();
    }
    @Test
    void findIfUserExist_ByUsername() {
        //arrange -> given
        staffRepo.save(staffEntity);
        //act -> when


        //assert -> then
    }
}