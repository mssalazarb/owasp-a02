package com.a02.owasp.infraestructure.controllers;

import com.a02.owasp.domain.model.CreditCard;
import com.a02.owasp.domain.ports.in.EncryptionService;
import com.a02.owasp.domain.ports.out.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;
    private final EncryptionService encryptionService;

    @PostMapping
    public String saveCreditCard(@RequestBody CreditCard card) throws Exception {
        card.setCardNumber(encryptionService.encrypt(card.getCardNumber()));
        CreditCard creditCard = this.creditCardRepository.save(card);

        return this.encryptionService.decrypt(creditCard.getCardNumber());
    }

    @GetMapping
    public CreditCard getCreditCard(@RequestParam Long id) throws Exception {
        CreditCard creditCard = this.creditCardRepository.findCreditCardById(id);
        creditCard.setCardNumber(this.encryptionService.decrypt(creditCard.getCardNumber()));

        return  creditCard;
    }
}
