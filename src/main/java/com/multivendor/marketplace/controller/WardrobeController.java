package com.multivendor.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multivendor.marketplace.dto.UserDto;
import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.model.Wardrobe;
import com.multivendor.marketplace.service.UserService;
import com.multivendor.marketplace.service.WardrobeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/wardrobe")
@CrossOrigin("http://localhost:4200")
public class WardrobeController {
    
    @Autowired
    private WardrobeService wardrobeService;

    @Autowired
    private UserService userSeriService;


    @PostMapping("/create")
    public ResponseEntity<String> saveWardrobe(@RequestBody Wardrobe wardrobe){

        log.info("Processing the wardrobe.");
        
        User user = this.userSeriService.getUserById(wardrobe.getUser().getUserId());

        
        if(user==null){
            return ResponseEntity.badRequest().body("User not found with given id");
        }
        User user2 = new User();
        user2.setUserId(user.getUserId());
        user2.setUserName(user.getUserName());
        wardrobe.setUser(user2);
        var result = this.wardrobeService.createWardrobe(wardrobe);

        if(result == null){
            return ResponseEntity.badRequest().body("Wardrobe Creation Error");
        }
        return ResponseEntity.status(201).body("Created Successfully");
    }
}
