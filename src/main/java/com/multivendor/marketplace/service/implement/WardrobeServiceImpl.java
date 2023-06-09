package com.multivendor.marketplace.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.Wardrobe;
import com.multivendor.marketplace.repository.WardrobeRepository;
import com.multivendor.marketplace.service.WardrobeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WardrobeServiceImpl  implements WardrobeService{

    @Autowired
    private WardrobeRepository wardrobeRepository;
    
    @Override
    public Wardrobe createWardrobe(Wardrobe wardrobe) {

      log.info("Saving wardrobe in database");

      Wardrobe result = this.getByUserIdandWardrobeName(wardrobe.getUser().getUserId(), wardrobe.getTitle());
      log.info("Wardrobe {}",result);
      if(result!=null){
        return null;
      }
      String id = UUID.randomUUID().toString();
      wardrobe.setId(id);
      log.info("wardrobe to be save {}",wardrobe);
      
      return this.wardrobeRepository.save(wardrobe);
    }

    @Override
    public Wardrobe getWardrobeById(String id) {
        log.info("Getting wardrobe by id {} from database" ,id);

        return this.wardrobeRepository.findById(id).get();
    }

 
    @Override
    public Wardrobe getByUserIdandWardrobeName(String userId, String title) {
        
        log.info("Finding by userId and wardrobe title");
        return this.wardrobeRepository.findByUserUserIdAndTitle(userId, title);
    }

    
    @Override
    public List<Wardrobe> getWardrobeByUserId(String userId) {
        log.info("Finding wardrobe by userId");
        return this.wardrobeRepository.findAllByUserUserId(userId);
    }

    @Override
    public List<Wardrobe> getAllWardrobe() {
        log.info("Fetching all wardrobes");
        return this.wardrobeRepository.findAll();
    }

    @Override
    public void deleteWardrobe(String id) {

        log.info("deleting the wardrobe from database");
        Wardrobe found = this.wardrobeRepository.findById(id).get();

        if(found == null){
            log.error("No wardrobe found with given id {}",id);
        }
        this.wardrobeRepository.delete(found);
    }

    @Override
    public Wardrobe updatWardrobe(Wardrobe wardrobes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatWardrobe'");
    }


    
}
