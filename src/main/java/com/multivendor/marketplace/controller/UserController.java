package com.multivendor.marketplace.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multivendor.marketplace.dto.UserDto;
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
    public ResponseEntity<String> resgisterUser(@RequestParam("file") MultipartFile profilePicture,String user) throws IOException{

        log.info("/POST : saving user");

        log.info("USER {}",user);
        User n_user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        n_user = objectMapper.readValue(user, User.class);


        log.info("Object Mapper {}",n_user);        
        User found = this.userService.getUserByEmail(n_user.getEmail());
        Role role = this.roleService.getRoleByName(default_role);
        

        if (found!=null){
            log.error("User Already Found with given Email: {}",n_user.getEmail());
            return ResponseEntity.badRequest().body("User already exist with given email");
        }
        if(role==null){
            log.error("User Creation Error. Role not found.");
            return ResponseEntity.badRequest().body("Role Error");
        }        
        log.info("File Name: {} and size {}",profilePicture.getOriginalFilename(),profilePicture.getSize());
        n_user.setProfilePicture(profilePicture.getBytes());
            log.info("Requesting the User Creation with email {}",n_user.getEmail());

            this.userService.createUser(n_user, role);
            return ResponseEntity.status(200).body("Success Fully created.");
        
    }

    @PostMapping("/{userId}/follow/{followedId}")
    ResponseEntity<?> makefollow(@PathVariable String userId,@PathVariable String followedId){

        log.info("Following User");
        this.userService.followUser(userId, followedId);
        return ResponseEntity.status(200).body("User followed SucessFully");
    }

    @GetMapping("/all/{userName}")
    public List<User> getUserByName(@PathVariable("userName") String username){

        List<User> result = this.userService.getAllUsersByUserNameLike(username);

        if(result == null){
            log.error("Users not found");
            return null;
        }
        log.info("User Found." );
        return result;
        
    }
//     @GetMapping("/{userId}/info")
// public ResponseEntity<Map<String, Object>> getUserFollowInfo(@PathVariable String userId) {
//     User user = this.userService.getUserById(userId);

//     if (user!=null) {
//         Set<User> followers = user.getFollowers();
//         Set<User> following = user.getFollowing();

//         Map<String, Object> response = new HashMap<>();
//         response.put("user", user.toString());
//         response.put("followers", followers);
//         response.put("following", following);

//         return ResponseEntity.ok(response);
//     } else {
//         return ResponseEntity.notFound().build();
//     }
// }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){

        User result = this.userService.getUserById(userId);

        if(result == null){
            log.error("Users not found");
            return ResponseEntity.badRequest().body("User not found");
        }
        log.info("User Found." );

        UserDto user = new UserDto(result.getUserId(),result.getUserName(),result.getEmail(),result.getProfilePicture(),result.getRole());
        return ResponseEntity.status(200).body(user);
        
    }
    @GetMapping("/all")
    List<User> Allusers(){
        log.info("Geting all users");
        return this.userService.getAllUsers();
    }
    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

}
