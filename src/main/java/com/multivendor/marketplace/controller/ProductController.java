package com.multivendor.marketplace.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
