package com.multivendor.marketplace.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.repository.RoleRepository;
import com.multivendor.marketplace.service.RoleService;
import com.multivendor.marketplace.service.UserService;




@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private String default_role = "USER";
    
    @PostMapping("/register")
    public ResponseEntity<String> resgisterUser(@RequestBody User user){

        log.info("User {}",user);

        User found = this.userService.getUserByEmail(user.getEmail());
        Role role = this.roleService.getRoleByName(default_role);
        if (found!=null){
            log.error("User Already Found with given Email: {}",user.getEmail());
            return ResponseEntity.badRequest().body("User already exist with given email");
        }
        if(role==null){
            log.error("User Creation Error. Role not found.");
            return ResponseEntity.badRequest().body("Role Error");
        }        
        
            log.info("Requesting the User Creation with email {}",user.getEmail());
            this.userService.createUser(user, role);
            return ResponseEntity.status(200).body("Success Fully created.");
        
    }

    @GetMapping("/{username}")
    public List<User> getuser(@PathVariable("userName") String username){

        List<User> result = this.userService.getAllUserByUserName(username);

        if(result == null){
            log.error("Users not found");
            return null;
        }
        log.info("User Found." );
        return result;
        
    }
    
    @GetMapping("/all")
    List<User> Allusers(){

        return this.userService.getAllUsers();
    }
    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

}
