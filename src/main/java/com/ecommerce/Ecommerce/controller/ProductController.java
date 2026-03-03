package com.ecommerce.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.services.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // View All Products
    @GetMapping
    public String getProducts(Model model) {

        List<Product> products =
                productService.getProducts();

        model.addAttribute("products", products);

        return "products";
    }


    // Add Product
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {

        productService.addProduct(product);

        return "redirect:/product";
    }


    // Get Single Product (Update Page)
    @GetMapping("/update/{id}")
    public String getProductById(
            @PathVariable int id,
            Model model) {

        Product product =
                productService.getProductById(id);

        model.addAttribute("product", product);

        return "productUpdate";
    }


    // Update Product
    @PostMapping("/update/{id}")
    public String updateProductByID(
            @PathVariable int id,
            @ModelAttribute Product product) {

        productService.updateProductById(id, product);

        return "redirect:/product";
    }


    // Delete Product
    @GetMapping("/delete/{id}")
    public String deleteProductById(
            @PathVariable int id) {

        productService.deleteProductById(id);

        return "redirect:/product";
    }

}