package com.multivendor.marketplace.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multivendor.marketplace.dto.ProductDto;
import com.multivendor.marketplace.dto.UserDto;
import com.multivendor.marketplace.dto.WardrobeDto;
import com.multivendor.marketplace.model.Product;
import com.multivendor.marketplace.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/product")
public class ProductController {
    

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    
    @PostMapping("/save")
    public ResponseEntity<String> saveFile(@RequestParam("image1") MultipartFile image1,@PathParam("image2") MultipartFile image2,String product) throws IOException{

    log.info("Saving the Product with iamges");

    Product n_product = new Product();
    ObjectMapper objectMapper = new ObjectMapper();
    n_product = objectMapper.readValue(product, Product.class);
    
    n_product.setImage1(image1.getBytes());
    n_product.setImage2(image2.getBytes());
    
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
            return ResponseEntity.badRequest().body("Products Fetching error");
        }        
        List<ProductDto> productsDto;
        
        productsDto = products.stream().map(
            product -> new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getBasePrice(),
                product.getImage1(),
                product.getImage2(),
                product.getDescription(),
                product.getStatus(),
                product.getReviews(),
                product.getCategory(),
                new WardrobeDto(
                    product.getWardrobe().getId(), product.getWardrobe().getTitle(),  product.getWardrobe().getDescription(),  product.getWardrobe().getCode(), 
                    new UserDto(product.getWardrobe().getUser().getUserId(),product.getWardrobe().getUser().getUserName(),product.getWardrobe().getUser().getEmail(),product.getWardrobe().getUser().getProfilePicture(),product.getWardrobe().getUser().getRole())
                    )
            )
        ).toList();
        return ResponseEntity.status(200).body(productsDto);
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


    @GetMapping("/wardrobe/{wardrobeId}")
    ResponseEntity<?> getAllProductsByWardrobeId(@PathVariable String wardrobeId){

        log.info("GET : fetchinfg all product by  wardrobe id {}",wardrobeId);

        List<Product> products = this.productService.getAllProductsByWardrobeId(wardrobeId);

        
        if(products == null){
            log.error("Products not found");
            return ResponseEntity.badRequest().body("Error");
        }
        //! converting product into its Dto
        List<ProductDto> productsDto;
        
        productsDto = products.stream().map(
            product -> new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getBasePrice(),
                product.getImage1(),
                product.getImage2(),
                product.getDescription(),
                product.getStatus(),
                product.getReviews(),
                product.getCategory(),
                new WardrobeDto(
                    product.getWardrobe().getId(), product.getWardrobe().getTitle(),  product.getWardrobe().getDescription(),  product.getWardrobe().getCode(), 
                    new UserDto(product.getWardrobe().getUser().getUserId(),product.getWardrobe().getUser().getUserName(),product.getWardrobe().getUser().getEmail(),product.getWardrobe().getUser().getProfilePicture(),product.getWardrobe().getUser().getRole())
                    )
            )
        ).toList();
        
        return ResponseEntity.status(200).body(productsDto);
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
