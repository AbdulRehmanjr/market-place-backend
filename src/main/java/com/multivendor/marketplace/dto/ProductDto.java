package com.multivendor.marketplace.dto;

import java.util.Arrays;

import com.multivendor.marketplace.model.Category;

public class ProductDto {
    
    private String productId;

    private String productName;

    private String basePrice;


    private byte[] image1;


    private byte[] image2;

    private String description;


    private String status="INSTOCK";

    private Double reviews = 0.0;


    private Category category;

        private WardrobeDto wardrobe;

        public ProductDto(String productId, String productName, String basePrice, byte[] image1, byte[] image2,
                String description, String status, Double reviews, Category category, WardrobeDto wardrobe) {
            this.productId = productId;
            this.productName = productName;
            this.basePrice = basePrice;
            this.image1 = image1;
            this.image2 = image2;
            this.description = description;
            this.status = status;
            this.reviews = reviews;
            this.category = category;
            this.wardrobe = wardrobe;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getReviews() {
            return reviews;
        }

        public void setReviews(Double reviews) {
            this.reviews = reviews;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public WardrobeDto getWardrobe() {
            return wardrobe;
        }

        public void setWardrobe(WardrobeDto wardrobe) {
            this.wardrobe = wardrobe;
        }

        @Override
        public String toString() {
            return "ProductDto [productId=" + productId + ", productName=" + productName + ", basePrice=" + basePrice
                    + ", image1=" + Arrays.toString(image1) + ", image2=" + Arrays.toString(image2) + ", description="
                    + description + ", status=" + status + ", reviews=" + reviews + ", category=" + category
                    + ", wardrobe=" + wardrobe + "]";
        }
}
