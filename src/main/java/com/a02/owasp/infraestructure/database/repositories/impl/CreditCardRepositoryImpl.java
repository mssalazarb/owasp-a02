package com.a02.owasp.infraestructure.database.repositories.impl;

import com.a02.owasp.domain.model.CreditCard;
import com.a02.owasp.domain.ports.out.CreditCardRepository;
import com.a02.owasp.infraestructure.database.entities.CreditCardEntity;
import com.a02.owasp.infraestructure.database.repositories.JpaCreditCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final JpaCreditCardRepository jpaCreditCardRepository;
    private final ObjectMapper mapper;

    @Override
    public CreditCard save(CreditCard creditCard) {
        CreditCardEntity creditCardEntity = this.jpaCreditCardRepository.save(mapper.convertValue(creditCard, CreditCardEntity.class));

        return mapper.convertValue(creditCardEntity, CreditCard.class);
    }

    @Override
    public CreditCard findCreditCardById(Long id) {
        Optional< CreditCardEntity> creditCardEntity = this.jpaCreditCardRepository.findCreditCardById(id);

        return creditCardEntity.map( creditCard -> mapper.convertValue(creditCard, CreditCard.class)).orElse(null);
    }
}
