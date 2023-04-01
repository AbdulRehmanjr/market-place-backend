package com.multivendor.marketplace.service.implement;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.Category;
import com.multivendor.marketplace.repository.CategoryRepository;
import com.multivendor.marketplace.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {


    private Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    private CategoryRepository catRepo;

    

    @Override
    public Category addCategory(Category category) {
        
        
        log.info("Saving the New Category");

        String id = UUID.randomUUID().toString();

        category.setCategoryId(id);
        
        return this.catRepo.save(category);

    }

    @Override
    public Category updateCategory(Category category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Getting All Categories");

        List<Category> categories = this.catRepo.findAll();

        if(categories == null){
            log.info("Category not found");
            return null;
        }
        return categories;
    }

    @Override
    public Category getCategoryById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    @Override
    public Category getCategoryByName(String title) {
        
        log.info("Getting the Category by name {}",title);

        Category category = this.catRepo.findByName(title);

        if(category == null){
            log.info("Category not found");
            return null;
        }
        return category;
    }

    @Override
    public void deleteCategory(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }
    
}
