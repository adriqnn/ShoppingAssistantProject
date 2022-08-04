package com.example.shoppingassistant.repository;

import com.example.shoppingassistant.model.entity.ActiveDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveDiscountRepository extends JpaRepository<ActiveDiscount, Long> {
}
