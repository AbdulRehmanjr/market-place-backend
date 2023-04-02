package com.multivendor.marketplace.service.implement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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


    
}
