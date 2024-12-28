package com.a02.owasp.domain.ports.out;

import com.a02.owasp.domain.model.CreditCard;

import java.util.List;

public interface CreditCardRepository {
    CreditCard save(CreditCard creditCard);
    List<CreditCard> findCreditCardByCard(String id);
}
