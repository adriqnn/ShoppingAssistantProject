package com.example.shoppingassistant.repository;

import com.example.shoppingassistant.model.entity.StoreProductCategoryPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreProductCategoryPricingRepository extends JpaRepository<StoreProductCategoryPricing, Long> {
}
