package com.codemiser.e_commerce_app.repositories;

import com.codemiser.e_commerce_app.modals.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
