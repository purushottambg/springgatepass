package com.gatepass.configs;

import org.springframework.context.annotation.Bean;

public class MapperConfigs {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper;
    }
}
