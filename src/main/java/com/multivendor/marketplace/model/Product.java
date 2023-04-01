package com.multivendor.marketplace.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCTTABLE")
public class Product {
    
    @Id
    private String productId;

    private String productName;

    private String basePrice;

    @Lob
    @Column(length = 10240)
    private byte[] image1;
    
    @Lob
    @Column(length = 10240)
    private byte[] image2;

    private String description;

    private Double reviews = 0.0;


    @ManyToOne
    private Category category;

    
    public Double getReviews() {
        return reviews;
    }

    public void setReviews(Double reviews) {
        this.reviews = reviews;
    }

    

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", basePrice=" + basePrice
                + ", image1=" + Arrays.toString(image1) + ", image2=" + Arrays.toString(image2) + ", description="
                + description + ", reviews=" + reviews + ", category=" + category + "]";
    }

}
