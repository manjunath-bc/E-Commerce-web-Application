package com.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Ecommerce.models.Cart;
import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.models.User;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
}
