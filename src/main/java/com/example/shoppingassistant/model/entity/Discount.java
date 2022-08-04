package com.example.shoppingassistant.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_type", unique = true, nullable = false)
    private String discountType;

    @Column(name = "discount_view", unique = true, nullable = false)
    private int discountValue;

    @Column(name = "discount_picture", nullable = false)
    private String discountPicture;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    public Discount() {
    }

    public Discount(String discountType, int discountValue, String discountPicture, String description) {
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.discountPicture = discountPicture;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Discount setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDiscountType() {
        return discountType;
    }

    public Discount setDiscountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public Discount setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
        return this;
    }

    public String getDiscountPicture() {
        return discountPicture;
    }

    public Discount setDiscountPicture(String discountPicture) {
        this.discountPicture = discountPicture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Discount setDescription(String description) {
        this.description = description;
        return this;
    }


}
