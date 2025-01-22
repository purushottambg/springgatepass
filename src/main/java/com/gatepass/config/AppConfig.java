package com.gatepass.config;

import com.gatepass.auth.AuditorAwareImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImp();
    }
}
