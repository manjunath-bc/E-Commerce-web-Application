package com.ecommerce.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.Ecommerce.models.Category;
import com.ecommerce.Ecommerce.services.CategoryService;


@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // View All Categories
    @GetMapping
    public String getCategories(Model model) {

        List<Category> categories =
                categoryService.getCategories();

        model.addAttribute("categories", categories);

        return "categories";
    }


    // Add Category
    @PostMapping("/add")
    public String addCategory(
            @RequestParam("categoryname") String name) {

        categoryService.addCategory(name);

        return "redirect:/category";
    }


    // Delete Category
    @GetMapping("/delete/{id}")
    public String deleteCategoryById(
            @PathVariable int id) {

        categoryService.deleteCategoryById(id);

        return "redirect:/category";
    }


    // Update Category
    @PostMapping("/update")
    public String updateCategoryById(
            @RequestParam int id,
            @RequestParam String name) {

        categoryService.updateCategoryById(id, name);

        return "redirect:/category";
    }

}