package com.gatepass.repository;

import com.gatepass.models.DepartmentEntity;
import com.gatepass.models.HODEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class HODRepoTest {

    @Autowired
    private HODRepo hodRepo;

    @Autowired
    private DeptRepo deptRepo; // Inject repository, not entity

    private DepartmentEntity department;
    private HODEntity hod1, hod2;

    @BeforeEach
    void setUp() {
        // Step 1: Create and save a department
        department = DepartmentEntity.builder()
                .deptname("Cardiology")
                .address("Block A, 2nd Floor")
                .description("Handles heart-related treatments")
                .hodList(new ArrayList<>()) // Initialize the list to avoid NullPointerException
                .build();
        department = deptRepo.save(department); // Save & retrieve to ensure it's managed

        // Step 2: Create HODs linked to this department
        hod1 = HODEntity.builder()
                .userName("hod_aniket")
                .fname("Aniket")
                .lname("Darade")
                .designation("Senior Doctor")
                .phone("9876543210")
                .email("aniket@gmail.com")
                .password("securePass")
                .departmentEntity(department)
                .build();

        hod2 = HODEntity.builder()
                .userName("hod_rohan")
                .fname("Rohan")
                .lname("Sharma")
                .designation("Consultant")
                .phone("9123456789")
                .email("rohan@gmail.com")
                .password("anotherPass")
                .departmentEntity(department)
                .build();

        // Step 3: Maintain bidirectional consistency
        department.getHodList().add(hod1);
        department.getHodList().add(hod2);

        // Step 4: Save HODs (Department has CascadeType.ALL, so they are persisted automatically)
        hodRepo.save(hod1);
        hodRepo.save(hod2);
    }

    @Test
    public void findByUSername(){
        deptRepo.save(department);
        hodRepo.save(hod1);
        hodRepo.save(hod2);

    }
    @Test
    void findByuserName() {
        //Arrange | Given
        hodRepo.save(hod1);
        hodRepo.save(hod2);
        //Act | When
        Optional<HODEntity> hodEntity1 = hodRepo.findByuserName(hod1.getUserName());
        hodEntity1.ifPresent(hodEntity2 -> {
            hod1 = hodEntity2;
        });
        //Assert | then
        Assertions.assertThat(hod1).isNotNull();
    }
}
