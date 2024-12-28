package com.a02.owasp.infraestructure.controllers;

import com.a02.owasp.domain.model.CreditCard;
import com.a02.owasp.domain.ports.in.EncryptionService;
import com.a02.owasp.domain.ports.out.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<CreditCard> getCreditCard(@RequestParam String cardNumber) {
        List<CreditCard> creditCards = this.creditCardRepository.findCreditCardByCard(cardNumber);
        creditCards.forEach(creditCard -> {
            try {
                creditCard
                        .setCardNumber(this.encryptionService.decrypt(creditCard.getCardNumber()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return  creditCards;
    }
}
