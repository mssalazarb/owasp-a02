package com.a02.owasp.infraestructure.beans;

import com.a02.owasp.application.EncryptionServiceImpl;
import com.a02.owasp.domain.ports.in.EncryptionService;
import com.a02.owasp.domain.ports.out.CreditCardRepository;
import com.a02.owasp.infraestructure.database.repositories.JpaCreditCardRepository;
import com.a02.owasp.infraestructure.database.repositories.impl.CreditCardRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final JpaCreditCardRepository jpaUserRepository;
    private final ObjectMapper mapper;

    @Bean
    public CreditCardRepository userRepository() {
        return new CreditCardRepositoryImpl(jpaUserRepository, mapper);
    }

    @Bean
    public EncryptionService encryptionService() {
        return new EncryptionServiceImpl();
    }
}
