package com.ecommerce.Ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // Get All Products
    public List<Product> getProducts() {

        return productRepository.findAll();
    }


    // Add Product
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }


    // Get Product By Id
    public Product getProductById(int id) {

        return productRepository
                .findById((long) id)
                .orElse(null);
    }


    // Update Product
    public Product updateProductById(int id,
                                 Product product) {

        product.setId((long) id);

        return productRepository.save(product);
    }


    // Delete Product
    public boolean deleteProductById(int id) {

        productRepository.deleteById((long) id);

        return true;
    }

}