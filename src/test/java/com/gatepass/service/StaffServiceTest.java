package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock    //Don't use actual Repo just mock it
    private StaffRepo staffRepo;

    @InjectMocks  //Injected service
    private StaffService staffService;

    StaffEntity staff;

    @Spy
    ModelMapper modelMapper;

    @BeforeEach
    void createNewStaff(){
        MockitoAnnotations.initMocks(this);
         staff = StaffEntity.builder()
                .staffid(1L)
                 .username("Pandu")
                .fname("oe")
                .sname("e")
                .lname("fd")
                .designation("Desi")
                .phone("8605")
                .email("p@gmail.com")
                .password("pass")
                .build();
    }

    @Test
    void existByUserName() {
        //Give
        String username = "Pandu";

        //Stubbing  - Define the behaviour of the mocked object
         when(staffRepo.findByUsername(username)).thenReturn(Optional.of(staff));

         //Act
         LoginDTO loginDTO = staffService.existByUserName2(username);

         //Assert

        Assertions.assertThat(loginDTO.getUserName()).isEqualTo(username);
        Assertions.assertThat(loginDTO).isNotNull();

    }

//    @Test
//    @DisplayName("This is just the Happy Case")
//    void happyCaseOfMockito() {
//        List<String> username = new ArrayList<String>(Arrays.asList("One", "Two"));
//        //when(staffRepo.nothingButMockito(username)).thenReturn(Arrays.asList("one"));
//
//        List<String> loginDTO = staffService.nothing();
//
//        assertNotNull(loginDTO);
//        //assertEquals( String.class);
//    }
}