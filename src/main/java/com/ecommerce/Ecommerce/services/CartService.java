package com.ecommerce.Ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.models.Cart;
import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.repository.CartRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
	CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    // Add Cart
    public Cart addCart(Cart cart) {

        return cartRepository.save(cart);
    }


    // Get All Carts
    public List<Cart> getCarts() {

        return cartRepository.findAll();
    }

    public Product getProductById(Long id){

        return productRepository.findById(id).orElse(null);
    }

    
    // Update Cart
    public void updateCart(Cart cart) {

        cartRepository.save(cart);
    }


    // Delete Cart
    public void deleteCart(Cart cart) {

        cartRepository.delete(cart);
    }

}