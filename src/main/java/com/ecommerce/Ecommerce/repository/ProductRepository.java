package com.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Ecommerce.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
