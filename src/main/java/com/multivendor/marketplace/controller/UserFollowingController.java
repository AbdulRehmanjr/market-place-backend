package com.multivendor.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.service.UserFollowerService;
import com.multivendor.marketplace.service.UserFollowingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/following")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class UserFollowingController {

    @Autowired
    private UserFollowingService ufs;

    @PostMapping("/user/{userId}/follow/{followingId}")
    ResponseEntity<?> followUser(@PathVariable String userId,@PathVariable String followingId) {

        log.info("/POST : making follow request");

        try {
            this.ufs.followUser(userId, followingId);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error in following");
        }
        return ResponseEntity.status(200).body("Followed");
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<?> getAllFollowing(@PathVariable String userId) {
        log.info("/POST : making follow request");

        
        List<User> users =     this.ufs.fetchAllFollowers(userId);
        
        if(users == null){
            return ResponseEntity.status(200).body("No following found.");
        }
        return ResponseEntity.status(200).body(users);
    }
}
