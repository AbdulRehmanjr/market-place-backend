package com.multivendor.marketplace.service.implement;


import java.util.List;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.repository.UserRepository;
import com.multivendor.marketplace.service.UserService;




@Service
public class UserServicesImp implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServicesImp.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User createUser(User user,Role role){
        
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);

        log.info("Saving the User with Id {}",randomUserId);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(role);
        

        return this.userRepo.save(user);
    }

    @Override
    public User getUserById(String userId) {
        log.info("Finding user with id {}",userId);
        
        return this.userRepo.findById(userId).get();
    }
    
    @Override
    public List<User> getAllUsersByUserNameLike(String userNameLike) {
     
        log.info("getAllUsersByUserNameLike");
        
        return this.userRepo.findByUserNameContains(userNameLike);
    }

    @Override
    public List<User> getAllUserByUserName(String userName){

        log.info("Getting all Users with given userName: {}",userName);

        List<User> users = this.userRepo.findByUserName(userName);

        if(users==null){
            log.info("No User found with given user name");
            return null;
        }
        return users;
    }
    @Override
    public void deleteUser(String id) {
        log.info("DELETE with id :{}",id);
        this.userRepo.deleteById(id);
    }
    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return this.userRepo.findAll();
    }
    @Override
    public User getUserByEmail(String email) {
        log.info("Getting By email");
        return this.userRepo.findByEmail(email);
    }

    @Override
    public void followUser(String followBy, String followedId) {

        log.info("User Id {} Followed By {}",followedId,followBy);

        User user = this.userRepo.findById(followBy).get();
        User followedUser = this.userRepo.findById(followedId).get();
        // if (user.equals(followedUser)) {
        //     return ResponseEntity.badRequest().body("You cannot follow yourself");
        // }
        // user.getFollowing().add(followedUser);
        // followedUser.getFollowers().add(user);
        this.userRepo.save(user);
        this.userRepo.save(followedUser);
        


    }


   

    
}
