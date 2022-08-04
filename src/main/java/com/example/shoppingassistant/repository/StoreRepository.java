package com.example.shoppingassistant.repository;

import com.example.shoppingassistant.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByName(String name);

    @Query("SELECT s.name FROM Store s")
    List<String> getAllStoresNames();
}
