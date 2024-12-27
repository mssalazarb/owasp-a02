package com.a02.owasp.infraestructure.controllers;

import com.a02.owasp.domain.model.CreditCard;
import com.a02.owasp.domain.ports.in.EncryptionService;
import com.a02.owasp.domain.ports.out.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;
    private final EncryptionService encryptionService;

    @GetMapping("/creditCard")
    public String getCreditCard(@RequestParam Long id) throws Exception {
        CreditCard creditCard = this.creditCardRepository.findCreditCardById(id);

        return this.encryptionService.decrypt(creditCard.getCardNumber());
    }
}
