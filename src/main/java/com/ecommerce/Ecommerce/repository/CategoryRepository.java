package com.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Ecommerce.models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
