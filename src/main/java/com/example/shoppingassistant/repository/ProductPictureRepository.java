package com.example.shoppingassistant.repository;

import com.example.shoppingassistant.model.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Long> {

    Optional<ProductPicture> findByTag(String tag);
}
