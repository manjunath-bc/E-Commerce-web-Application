package com.ecommerce.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.Ecommerce.models.Category;
import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.services.CategoryService;
import com.ecommerce.Ecommerce.services.ProductService;
import com.ecommerce.Ecommerce.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public AdminController(UserService userService,
                           CategoryService categoryService,
                           ProductService productService) {

        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }


    // Admin Dashboard
    @GetMapping({"/","/Dashboard"})
    public ModelAndView adminHome() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        ModelAndView mv =
                new ModelAndView("adminHome");

        mv.addObject("admin",
                authentication.getName());

        return mv;
    }


    // Admin Login Page
    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(required = false) String error) {

        ModelAndView mv =
                new ModelAndView("adminlogin");

        if ("true".equals(error)) {
            mv.addObject("msg",
                    "Invalid username or password");
        }

        return mv;
    }


    // View Categories
    @GetMapping("/categories")
    public ModelAndView categories() {

        ModelAndView mv =
                new ModelAndView("categories");

        mv.addObject("categories",
                categoryService.getCategories());

        return mv;
    }


    // Add Category
    @PostMapping("/categories")
    public String addCategory(
            @RequestParam("categoryname") String name) {

        categoryService.addCategory(name);

        return "redirect:/admin/categories";
    }


    // Delete Category
    @GetMapping("/categories/delete")
    public String deleteCategory(
            @RequestParam int id) {

        categoryService.deleteCategoryById(id);

        return "redirect:/admin/categories";
    }


    // Update Category
    @PostMapping("/categories/update")
    public String updateCategory(
            @RequestParam int categoryid,
            @RequestParam String categoryname) {

        categoryService.updateCategoryById(
                categoryid,
                categoryname);

        return "redirect:/admin/categories";
    }


    // View Products
    @GetMapping("/products")
    public ModelAndView products() {

        ModelAndView mv =
                new ModelAndView("products");

        mv.addObject("products",
                productService.getProducts());

        return mv;
    }


    // Add Product Page
    @GetMapping("/products/add")
    public ModelAndView addProductPage() {

        ModelAndView mv =
                new ModelAndView("productsAdd");

        mv.addObject("categories",
                categoryService.getCategories());

        return mv;
    }


    // Add Product
    @PostMapping("/products/add")
    public String addProduct(

            @RequestParam String name,
            @RequestParam int categoryid,
            @RequestParam int price,
            @RequestParam int weight,
            @RequestParam int quantity,
            @RequestParam String description,
            @RequestParam String productImage) {

        Category category =
                categoryService.getCategoryById(categoryid);

        Product product =
                new Product();

        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setWeight(weight);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setImage(productImage);

        productService.addProduct(product);

        return "redirect:/admin/products";
    }


    // Update Product Page
    @GetMapping("/products/update/{id}")
    public ModelAndView updateProductPage(
            @PathVariable int id) {

        ModelAndView mv =
                new ModelAndView("productsUpdate");

        mv.addObject("product",
                productService.getProductById(id));

        mv.addObject("categories",
                categoryService.getCategories());

        return mv;
    }


    // Update Product
    @PostMapping("/products/update/{id}")
    public String updateProduct(
            @PathVariable int id,
            @ModelAttribute Product product) {

        productService.updateProductById(id, product);

        return "redirect:/admin/products";
    }


    // Delete Product
    @GetMapping("/products/delete")
    public String deleteProduct(
            @RequestParam int id) {

        productService.deleteProductById(id);

        return "redirect:/admin/products";
    }


    // View Customers
    @GetMapping("/customers")
    public ModelAndView customers() {

        ModelAndView mv =
                new ModelAndView("displayCustomers");

        mv.addObject("customers",
                userService.getUsers());

        return mv;
    }

}