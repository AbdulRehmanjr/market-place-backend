package com.multivendor.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.Category;
import com.multivendor.marketplace.model.Product;



public interface ProductRepository  extends JpaRepository<Product,String>{
        
    public List<Product> findAllByCategory(Category category);

    public List<Product> findAllByProductName(String name);

    List<Product> findAllByWardrobeId(String wardrobeId);
}
