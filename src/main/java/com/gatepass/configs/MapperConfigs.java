package com.gatepass.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {

    // Model mapper is a library that can be used to map one object to another
    // For example here we need to map an Entity to the DTO

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
