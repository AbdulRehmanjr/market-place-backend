package com.multivendor.marketplace.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, String>{


    public Category findByName(String titleName);    
}
