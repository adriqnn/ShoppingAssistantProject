package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.DiscountNotFoundException;
import com.example.shoppingassistant.model.entity.Discount;
import com.example.shoppingassistant.repository.DiscountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public void initializeAllDiscountsForTheApplication() {
        if(this.discountRepository.count() == 0){
            for (int i = 1; i <= 50; i++) {

                String discountType = i + "%";
                String discountPicture = "/images/discount.png";
                int generatedVoucher = discountType.hashCode();
                String description = discountType + " Store discount just today! One time use voucher code - " + generatedVoucher + "!";

                Discount discount = new Discount(discountType, i, discountPicture, description);
                this.discountRepository.save(discount);
            }
        }
    }

    public Discount findById(long id) {
        return this.discountRepository.findById(id).orElseThrow(() -> new DiscountNotFoundException("Discount with id: " + id + " was not found."));
    }
}
