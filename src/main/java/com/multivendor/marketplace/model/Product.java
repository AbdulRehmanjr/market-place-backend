package com.multivendor.marketplace.model;

import java.util.Arrays;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTTABLE")
public class Product {

    @Id
    private String productId;

    private String productName;

    private String basePrice;

    @Lob
@Column(columnDefinition = "LONGBLOB")
    private byte[] image1;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image2;

    private String description;


    private String status="INSTOCK";

    private Double reviews = 0.0;

    @ManyToOne
    private Category category;

    @ManyToOne
        private Wardrobe wardrobe;

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


    public Wardrobe getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", basePrice=" + basePrice
                +", description="
                + description + ", reviews=" + reviews + ", category=" + category + ", wardrobe=" + wardrobe + "]";
    }

}
