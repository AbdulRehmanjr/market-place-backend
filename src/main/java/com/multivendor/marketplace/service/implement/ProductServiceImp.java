package com.multivendor.marketplace.service.implement;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.Category;
import com.multivendor.marketplace.model.Product;
import com.multivendor.marketplace.repository.CategoryRepository;
import com.multivendor.marketplace.repository.ProductRepository;
import com.multivendor.marketplace.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

    private Logger log = LoggerFactory.getLogger(ProductServiceImp.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(Product product) {

        log.info("Saving the New Product");

        Category category = this.categoryRepository.findById(product.getCategory().getCategoryId()).get();

        String id = UUID.randomUUID().toString();

        product.setProductId(id);
        if (category == null) {
            log.error("Product Saing Error. Category Not Found.");
            return null;
        }

        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product Product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public List<Product> getAllByCategory(String categoryId) {

        log.info("Geting all products by category");
        
        Category category = this.categoryRepository.findById(categoryId).get();

        if (category == null) {
            log.error("Product Fetching Error. Category Not Found.");
            return null;
        }
        
        List<Product> products = this.productRepository.findByCategory(category);

        if (products == null) {
            log.error("No product with given category");
            return null;
        }

        return products;
    }

    @Override
    public List<Product> getAllByProducts() {

        log.info("Geting all Products");
        
        List<Product> products = this.productRepository.findAll();

        if (products == null) {
            log.error("No product with given category");
            return null;
        }

        return products;
    }

    @Override
    public Product getProductById(String id) {
        
        log.info("Getting Product by Id {}",id);

        Product product = this.productRepository.findById(id).get();

        if(product == null){
            log.error("Product not found");
            return null;
        }
        return product;
    }

    @Override
    public List<Product> getProductByName(String title) {
        log.info("Getting Product by Name {}",title);

        List<Product> products = this.productRepository.findByProductName(title);

        if(products == null){
            log.error("Product not found");
            return null;
        }
        return products;
    }

    @Override
    public void deleteProduct(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

}
