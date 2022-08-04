package com.example.shoppingassistant.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "active_discounts")
public class ActiveDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_type", nullable = false)
    private String discountType;

    @Column(name = "discountValue", nullable = false)
    private int discountValue;

    @Column(name = "discount_picture", nullable = false)
    private String discountPicture;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    public ActiveDiscount(String discountType, int discountValue, String discountPicture, String description) {
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.discountPicture = discountPicture;
        this.description = description;
    }

    public ActiveDiscount() {
    }

    public Long getId() {
        return id;
    }

    public ActiveDiscount setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDiscountType() {
        return discountType;
    }

    public ActiveDiscount setDiscountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public ActiveDiscount setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
        return this;
    }

    public String getDiscountPicture() {
        return discountPicture;
    }

    public ActiveDiscount setDiscountPicture(String discountPicture) {
        this.discountPicture = discountPicture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActiveDiscount setDescription(String description) {
        this.description = description;
        return this;
    }
}
