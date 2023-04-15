package com.multivendor.marketplace.model;




import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="WARDROBETABLE")
public class Wardrobe {

    @Id
    private String id;
    
    private String title;

    private String description;
    
    private String code;
    
    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "wardrobe")
    private Set<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Wardrobe [id=" + id + ", title=" + title + ", description=" + description + ", code=" + code + ", user="
                + user + "]";
    }

        
}
