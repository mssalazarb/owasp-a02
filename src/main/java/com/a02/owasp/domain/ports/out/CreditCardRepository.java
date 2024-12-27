package com.a02.owasp.domain.ports.out;

import com.a02.owasp.domain.model.CreditCard;

public interface CreditCardRepository {
    CreditCard findCreditCardById(Long id);
}
