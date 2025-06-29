package com.gatepass.config;

import com.gatepass.auth.AuditorAwareImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")

/*
Configuration class that enables to inject the beans like model mapper and audit aware implementation
 */

public class AppConfig {

    @Bean
    AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImp();
    }
}
