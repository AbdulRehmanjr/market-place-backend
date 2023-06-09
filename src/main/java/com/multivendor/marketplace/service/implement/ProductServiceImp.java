package com.multivendor.marketplace.service.implement;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.Category;
import com.multivendor.marketplace.model.Product;
import com.multivendor.marketplace.model.Wardrobe;
import com.multivendor.marketplace.repository.CategoryRepository;
import com.multivendor.marketplace.repository.ProductRepository;
import com.multivendor.marketplace.repository.WardrobeRepository;
import com.multivendor.marketplace.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

    private Logger log = LoggerFactory.getLogger(ProductServiceImp.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WardrobeRepository wardrobeRepository;

    @Override
    public Product addProduct(Product product) {

        log.info("Saving the New Product");
        Category category = new Category();
        try {
           category = this.categoryRepository.findById(product.getCategory().getCategoryId()).get();    
        } catch (Exception e) {
            log.error("No Catgeory with given id");
        }
        

        Wardrobe wardrobe = this.wardrobeRepository.findById(product.
        getWardrobe().getId()).get();
        String id = UUID.randomUUID().toString();

        product.setProductId(id);
        if (category == null || wardrobe == null) {
            log.error("Product Saving Error");
            return null;
        }

        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        log.info("Updating the product");

        Product old = this.productRepository.findById(product.getProductId()).get();

        if(old==null){
            log.info("Product not Found");
            return null;
        }
        if(product.getBasePrice()!=null){
            old.setBasePrice(product.getBasePrice());
        }

        if(product.getProductName()!=null){
            old.setProductName(product.getProductName());
        }
        if(product.getDescription()!=null){
            old.setDescription(product.getDescription());
        }
        if(product.getReviews()!=null){
            old.setReviews(product.getReviews());
        }
        if(product.getStatus()!=null){
            old.setStatus(product.getStatus());
        }
        return this.productRepository.save(old);
    }

    @Override
    public List<Product> getAllByCategory(String categoryId) {

        log.info("Geting all products by category");
        
        Category category = this.categoryRepository.findById(categoryId).get();

        if (category == null) {
            log.error("Product Fetching Error. Category Not Found.");
            return null;
        }
        
        List<Product> products = this.productRepository.findAllByCategory(category);


        if (products == null) {
            log.error("No product with given category");
            return null;
        }

        return products;
    }

    @Override
    public List<Product> getAllProducts() {

        log.info("Fetching products from database.");
        
        List<Product> products = this.productRepository.findAll();

        if (products == null) {
            log.error("No product  found");
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

        List<Product> products = this.productRepository.findAllByProductName(title);

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

    @Override
    public List<Product> getAllProductsByWardrobeId(String wardrobeId) {
        List<Product> products = this.productRepository.findAllByWardrobeId(wardrobeId);

        if(products==null){
            log.error("No products found with given wardrobe");
            return null;
        }

        return products;
    }

}
