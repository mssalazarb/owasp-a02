package com.a02.owasp.infraestructure.database.repositories;

import com.a02.owasp.infraestructure.database.entities.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
    @Query(value = "select * from cards where card_number = ''?1", nativeQuery = true)
    List<CreditCardEntity> findCreditCardByCard(String cardNumber);
}
