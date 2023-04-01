package com.multivendor.marketplace.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "CATEGORYTABLE")
public class Category {


    @Id
    private String categoryId;

    private String  name;

    @JsonIgnore
    @Transient
    @OneToMany
    private Set<Product> products = new HashSet<>();


    public String getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Set<Product> getProducts() {
        return products;
    }


    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", name=" + name + ", products=" + products + "]";
    }

    
    
}
