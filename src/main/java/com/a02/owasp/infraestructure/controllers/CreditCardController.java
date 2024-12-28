package com.a02.owasp.infraestructure.controllers;

import com.a02.owasp.domain.model.CreditCard;
import com.a02.owasp.domain.ports.in.EncryptionService;
import com.a02.owasp.domain.ports.out.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
@Log4j2
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;
    private final EncryptionService encryptionService;

    @PostMapping
    public String saveCreditCard(@RequestBody CreditCard card) throws Exception {
        card.setCardNumber(encryptionService.encrypt(card.getCardNumber()));
        log.info("Saving credit card: {}", card.getCardNumber());
        CreditCard creditCard = this.creditCardRepository.save(card);

        return this.encryptionService.decrypt(creditCard.getCardNumber());
    }

    @GetMapping
    public CreditCard getCreditCard(@RequestParam Long id) {
        CreditCard creditCard = this.creditCardRepository.findCreditCardByCard(id);
        try {
            creditCard
                    .setCardNumber(this.encryptionService.decrypt(creditCard.getCardNumber()));
            log.info("Returning credit card: {}", creditCard.getCardNumber());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return  creditCard;
    }
}
