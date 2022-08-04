package com.example.shoppingassistant.repository;

import com.example.shoppingassistant.model.entity.ProductCategory;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByProductCategory(ProductCategoryEnum productCategory);

}
