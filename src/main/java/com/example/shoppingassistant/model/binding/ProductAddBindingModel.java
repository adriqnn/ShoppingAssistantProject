package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.model.validation.BigDecimalNotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductAddBindingModel {

    @NotBlank(message = "Cannot be empty.")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters.")
    private String name;

    @BigDecimalNotEmpty(message = "Cannot be empty.")
    @DecimalMin(value = "0",message = "Price must be positive.")
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "The date cannot be in the past.")
    private LocalDate neededBefore;

    @NotNull(message = "You must select the category.")
    private ProductCategoryEnum productCategory;

    public ProductAddBindingModel(String name, BigDecimal price, LocalDate neededBefore, ProductCategoryEnum productCategory) {
        this.name = name;
        this.price = price;
        this.neededBefore = neededBefore;
        this.productCategory = productCategory;
    }

    public ProductAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ProductAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getNeededBefore() {
        return neededBefore;
    }

    public ProductAddBindingModel setNeededBefore(LocalDate neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public ProductCategoryEnum getProductCategory() {
        return productCategory;
    }

    public ProductAddBindingModel setProductCategory(ProductCategoryEnum productCategory) {
        this.productCategory = productCategory;
        return this;
    }
}
