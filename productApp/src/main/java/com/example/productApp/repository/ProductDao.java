package com.example.productApp.repository;

import com.example.productApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
    List<Product> findByProductName(String productName);
    //void deleteByProductName(String productName);
}