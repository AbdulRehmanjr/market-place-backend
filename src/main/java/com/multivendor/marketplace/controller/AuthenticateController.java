package com.multivendor.marketplace.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multivendor.marketplace.config.jwt.JwtUtil;
import com.multivendor.marketplace.model.JwtRequest;
import com.multivendor.marketplace.model.JwtResponse;
import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.service.UserService;
import com.multivendor.marketplace.service.implement.UserDetailServiceImpl;

@RestController
@RequestMapping("/token")
@CrossOrigin("http://localhost:4200")
public class AuthenticateController {
    
    private Logger log = LoggerFactory.getLogger(AuthenticateController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    // generate token 
    @PostMapping("/generate")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request){

        log.info("Data from Request {}",request);
            try {
                
                    authentication(request.getemail(), request.getPassword());
                    log.info("Authentication {} ",request.getemail());
            } catch (Exception e) {
                    log.error("User not found exception {}");
                return ResponseEntity.badRequest().body("Error....");
            }

            // authentication successful s
         UserDetails user =  this.userDetailsService.loadUserByUsername(request.getemail());

          log.info("user: "+user.getUsername()+" "+user.getPassword());

          String token =   this.jwtUtil.generateToken(user);

        return ResponseEntity.status(200).body(new JwtResponse(token));
        
    }




    private void authentication(String username, String password) throws Exception {
            
        try {
            log.info("Authentication {} in process with password: {} ",username,password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
        } catch (DisabledException e) {
            log.error("USER DISABLED  {} ",e.getMessage() );
            throw new Exception("USER DISABLED");
        }
        catch(BadCredentialsException e){
            log.error("INVALID CREDENTIALS {} , cause{}",e.getMessage(), e.getCause());
            throw new Exception("INVALID CREDENTIALS");
        }

    }

    @PostMapping("/current-user")
    public User getCurrentUser(@RequestBody JwtRequest request){
        log.info("calling current user");
        User user = this.userService.getUserByEmail(request.getemail());
        log.info("USER {}",user);
        return user;
    }
}
