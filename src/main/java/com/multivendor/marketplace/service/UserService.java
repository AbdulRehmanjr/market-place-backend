package com.multivendor.marketplace.service;

import java.util.List;


import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.model.User;



public interface UserService {
    
    // create a new user
     User createUser(User user,Role role);

     List<User> getAllUserByUserName(String userName);

    void followUser(String followBy,String followedId);

     User getUserById(String userId);

     List<User> getAllUsersByUserNameLike(String userNameLike);

    //  get by email 
     User getUserByEmail(String email);

    // delete by id
     void deleteUser(String id);
    
    // public void getall
     List<User> getAllUsers();
}
