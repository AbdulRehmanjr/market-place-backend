package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.Wardrobe;

public interface WardrobeService {
    
    Wardrobe createWardrobe(Wardrobe wardrobe);

    Wardrobe getWardrobeById(String id);
    
    Wardrobe getByUserIdandWardrobeName(String userId,String title);

    List<Wardrobe> getWardrobeByUserId(String userId);

    List<Wardrobe> getAllWardrobe();

    void deleteWardrobe(String id);

    Wardrobe updatWardrobe(Wardrobe wardrobes);
}
