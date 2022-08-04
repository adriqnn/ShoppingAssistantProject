package com.example.shoppingassistant.model.entity;

import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "needed_before")
    private LocalDate neededBefore;

    @Column(name = "product_picture")
    private String productPicture;

    @ManyToOne
    private ProductCategory category;

    @ManyToOne
    private ProductPicture productPictureClass;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, LocalDate neededBefore, String productPicture, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.neededBefore = neededBefore;
        this.productPicture = productPicture;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getNeededBefore() {
        return neededBefore;
    }

    public Product setNeededBefore(LocalDate neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public Product setProductPicture(String productPicture) {
        this.productPicture = productPicture;
        return this;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Product setCategory(ProductCategory category) {
        this.category = category;
        return this;
    }

    public ProductPicture getProductPictureClass() {
        return productPictureClass;
    }

    public Product setProductPictureClass(ProductPicture productPictureClass) {
        this.productPictureClass = productPictureClass;
        return this;
    }


}
