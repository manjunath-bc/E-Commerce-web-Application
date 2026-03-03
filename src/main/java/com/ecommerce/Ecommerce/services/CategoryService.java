package com.ecommerce.Ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.models.Category;
import com.ecommerce.Ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // Add Category
    public Category addCategory(String name) {

        Category category = new Category();

        category.setName(name);

        return categoryRepository.save(category);
    }


    // Get All Categories
    public List<Category> getCategories() {

        return categoryRepository.findAll();
    }


    // Delete Category
    public Boolean deleteCategoryById(int id) {

        categoryRepository.deleteById((long) id);

        return true;
    }


    // Update Category
    public Category updateCategoryById(int id,
                                   String name) {

        Category category =
                categoryRepository
                .findById((long) id)
                .orElse(null);

        if(category != null){

            category.setName(name);

            return categoryRepository.save(category);

        }

        return null;
    }


    // Get Category By Id
    public Category getCategoryById(int id) {

        return categoryRepository
                .findById((long) id)
                .orElse(null);
    }

}