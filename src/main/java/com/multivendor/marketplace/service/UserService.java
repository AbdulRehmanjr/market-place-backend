package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.dto.UserDto;
import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.model.User;



public interface UserService {
    
    // create a new user
    public User createUser(User user,Role role);

    public List<User> getAllUserByUserName(String userName);

    public User getUserById(String userId);

    //  get by email 
    public User getUserByEmail(String email);

    // delete by id
    public void deleteUser(String id);
    
    // public void getall
    public List<User> getAllUsers();
}
