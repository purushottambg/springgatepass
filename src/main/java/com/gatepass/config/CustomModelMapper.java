package com.gatepass.config;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomModelMapper {

    @Bean
    @Qualifier("StaffEntityToLogInDTO")
    public ModelMapper EntityToLogINDTOMapping() {
        ModelMapper entityToLogINDTOMapping = new ModelMapper();
        entityToLogINDTOMapping.typeMap(StaffEntity.class, LoginDTO.class).addMappings(mapper -> {
            mapper.map(StaffEntity::getStaffid, LoginDTO::setId);
            mapper.map(StaffEntity::getUsername, LoginDTO::setUserName);
            mapper.map(StaffEntity::getPassword, LoginDTO::setPassword);
        });
        return entityToLogINDTOMapping;
    }

    @Bean
    @Qualifier("DefaultMapper")
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
