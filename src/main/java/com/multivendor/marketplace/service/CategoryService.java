package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.Category;





public interface CategoryService {
    
    public  Category addCategory(Category category);

    public Category updateCategory(Category category);

    public List<Category> getAllCategories();

    public Category getCategoryById(String id);

    public Category getCategoryByName(String title);
    
    public void deleteCategory(String id);

}
