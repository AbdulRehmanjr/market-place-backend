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

import com.multivendor.marketplace.dto.UserDto;
import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.service.UserFollowingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/following")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class UserFollowingController {

    @Autowired
    private UserFollowingService ufs;

    @PostMapping("/user/{currentId}/follow/{userId}")
    ResponseEntity<?> followUser(@PathVariable String currentId,@PathVariable String userId) {

        log.info("/POST : making follow request");

       
            this.ufs.followUser(currentId,userId);
       
        return ResponseEntity.status(200).body("Followed");
    }

    @PostMapping("/user/{currentId}/unfollow/{userId}")
    ResponseEntity<?> unfollowUser(@PathVariable String currentId,@PathVariable String userId) {

        log.info("/POST : making unfollow request");

       
            this.ufs.unfollowUser(currentId,userId);
       
        return ResponseEntity.status(200).body("unfollowed");
    }

    @GetMapping("/user/following/{userId}")
    ResponseEntity<?> getAllFollowing(@PathVariable String userId) {
        log.info("/GET : get all following");

        List<User> users =     this.ufs.fetchAllFollowings(userId);
        
        if(users == null){
            return ResponseEntity.status(200).body("No following found.");
        }

        //! Converting the user model into user dto
        List<UserDto> dtos;
                dtos = users.stream().map(u -> new UserDto(u.getUserId(),u.getUserName(),u.getEmail(),u.getProfilePicture(),u.getRole())).toList();
        
        return ResponseEntity.status(200).body(dtos);
        
    }

    @GetMapping("/user/follower/{userId}")
    ResponseEntity<?> getAllFollower(@PathVariable String userId) {
        log.info("/GET : get all followers");

        List<User> users = this.ufs.fetchAllFollowers(userId);     
        if(users == null){
            log.info("Error no follower found");
            return ResponseEntity.status(200).body("No follower found.");
        }
 
        //! Converting the user model into user dto
        List<UserDto> dtos;
                dtos = users.stream().map(u -> new UserDto(u.getUserId(),u.getUserName(),u.getEmail(),u.getProfilePicture(),u.getRole())).toList();
        return ResponseEntity.status(200).body(dtos);
    }
}
