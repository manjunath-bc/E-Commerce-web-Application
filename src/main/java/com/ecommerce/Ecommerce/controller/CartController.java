package com.ecommerce.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.Ecommerce.models.Cart;
import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.services.CartService;
import com.ecommerce.Ecommerce.services.UserService;


@Controller
@RequestMapping("/cart")
public class CartController {

    CartService cartService;
    UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    // View All Carts
    @GetMapping
    public String getCarts(Model model) {

        List<Cart> carts = cartService.getCarts();

        model.addAttribute("carts", carts);

        return "cart";
    }


    // Add Cart
    @PostMapping("/add")
    public String addCart(@ModelAttribute Cart cart) {

        cartService.addCart(cart);

        return "redirect:/cart";
    }

    @GetMapping("/products/addtocart")
    public String addToCart(
            @RequestParam Long id,
            Authentication auth) {

        User user =
        userService.getUserByUsername(auth.getName());

        Cart cart = new Cart();

        cart.setCustomer(user);

        cart.setProduct(
            cartService.getProductById(id)
        );

        cartService.addCart(cart);

        return "redirect:/user/products"; 
    }

    // Update Cart
    @PostMapping("/update")
    public String updateCart(@ModelAttribute Cart cart) {

        cartService.updateCart(cart);

        return "redirect:/cart";
    }


    // Delete Cart
    @PostMapping("/delete")
    public String deleteCart(@ModelAttribute Cart cart) {

        cartService.deleteCart(cart);

        return "redirect:/cart";
    }

}