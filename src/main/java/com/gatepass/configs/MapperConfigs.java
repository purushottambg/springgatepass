package com.gatepass.configs;

import org.springframework.context.annotation.Bean;

public class MapperConfigs {

    // Model mapper is a library that can be used to map one object to another
    // For example here we need to map an Entity to the DTO

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper;
    }
}
