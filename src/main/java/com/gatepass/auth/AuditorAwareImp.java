package com.gatepass.auth;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Optional;

@MappedSuperclass
@EnableJpaAuditing
@EntityListeners(EntityListeners.class)

public class AuditorAwareImp implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Purushottam Gutthe");
    }
}
