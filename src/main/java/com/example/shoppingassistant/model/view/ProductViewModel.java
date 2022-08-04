package com.example.shoppingassistant.model.view;

import java.math.BigDecimal;

public class ProductViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String productPicture;

    public ProductViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProductViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public ProductViewModel setProductPicture(String productPicture) {
        this.productPicture = productPicture;
        return this;
    }
}
