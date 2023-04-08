package com.multivendor.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.Wardrobe;

public interface WardrobeRepository  extends JpaRepository<Wardrobe,String>{
    
    List<Wardrobe> findByTitle(String title);

    Wardrobe findByUserUserIdAndTitle(String userId,String title);

    List<Wardrobe> findAllByUserUserId(String userId);
}
