package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.models.User;

import com.ecommerce.Ecommerce.services.ProductService;
import com.ecommerce.Ecommerce.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,
                          ProductService productService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mv = new ModelAndView("index");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        mv.addObject("username", username);
        mv.addObject("products", productService.getProducts());
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("userLogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Invalid username or password");
        }
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
    	 ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @PostMapping("/newuserregister")
    public ModelAndView registerUser(@ModelAttribute User user) {
        boolean exists = userService.checkUserExists(user.getUsername());
        if (!exists) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userService.addUser(user);
            return new ModelAndView("userLogin");
        } else {
            ModelAndView mv = new ModelAndView("register");
            mv.addObject("msg", "Username already exists");
            return mv;
        }
    }

    @PostMapping("/updateuser")
    public String updateUser(
            @RequestParam Long userid,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam(required = false) String password,
            @RequestParam String address) {

        User user = userService.getUserById(userid); // also add this to UserService
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userService.addUser(user);
        return "redirect:/profileDisplay";
    }
    
    @GetMapping("/user/products")
    public ModelAndView userProducts() {
        ModelAndView mv = new ModelAndView("uproduct");
        mv.addObject("products", productService.getProducts());
        return mv;
    }

    @GetMapping("/profileDisplay")
    public String profileDisplay(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        if (user != null) {
            model.addAttribute("userid", user.getId());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("address", user.getAddress());
        } else {
            model.addAttribute("msg", "User not found");
        }
        return "updateProfile";
    }

}
