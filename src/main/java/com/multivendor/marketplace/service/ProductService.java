package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.Product;

public interface ProductService {
    
    public  Product addProduct(Product Product);

    public Product updateProduct(Product Product);

    public List<Product> getAllByCategory(String categoryId);

    public List<Product> getAllProducts();

    public Product getProductById(String id);

    public List<Product> getProductByName(String title);
    
    public void deleteProduct(String id);


}
