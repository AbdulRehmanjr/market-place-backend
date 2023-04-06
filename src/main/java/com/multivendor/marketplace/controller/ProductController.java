package com.multivendor.marketplace.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multivendor.marketplace.model.Product;
import com.multivendor.marketplace.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    
    @PostMapping("/save")
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile[] savefile,String product) throws IOException{

    log.info("Saving the Product with iamges");

    Product n_product = new Product();
    ObjectMapper objectMapper = new ObjectMapper();
    n_product = objectMapper.readValue(product, Product.class);
    
    n_product.setImage1(savefile[0].getBytes());
    n_product.setImage2(savefile[1].getBytes());
    
    n_product = this.productService.addProduct(n_product);
    if(n_product==null){
        log.error("Error in saving Product");
        return ResponseEntity.badRequest().body("Error in saving the product");
    } 
    return ResponseEntity.status(200).body("Product saved successfully");
    }

    // getting all products

    @GetMapping("/all")
    ResponseEntity<?> fetchAllProducts(){

        log.info(" GET: Getting all products");

        List<Product> products = this.productService.getAllProducts();

        if(products == null){
            return ResponseEntity.badRequest().body("PRoducts Fetching error");
        }        
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/{productId}")
    ResponseEntity<?> fetchOneById(@PathVariable String productId){

        log.info("GET : get one product by id : {}",productId);

        Product product = this.productService.getProductById(productId);

        if(product ==null){
            return ResponseEntity.badRequest().body("Error in fetching.");
        }

        return ResponseEntity.status(200).body(product);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateProduct(@RequestBody Product product){

        log.info("GET : get one product by id : {}",product.getProductId());

        Product updated = this.productService.updateProduct(product);

        if(updated == null){
            return ResponseEntity.badRequest().body("Error product not found");
        }
        return ResponseEntity.status(200).body(updated);
    }
}
