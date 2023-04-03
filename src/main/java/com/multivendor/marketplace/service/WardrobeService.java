package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.Wardrobe;

public interface WardrobeService {
    
    Wardrobe createWardrobe(Wardrobe wardrobe);

    Wardrobe getByUserIdandWardrobeName(String userId,String title);

    List<Wardrobe> getWardrobeByUserId(String userId);

    List<Wardrobe> getAllWardrobe();

    void deleteWardrobe();

    Wardrobe updatWardrobe(Wardrobe wardrobes);
}
