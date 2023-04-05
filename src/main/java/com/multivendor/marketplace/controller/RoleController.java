package com.multivendor.marketplace.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.service.RoleService;


@RestController
@RequestMapping("/role")
@CrossOrigin("http://localhost:4200")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    

    @PostMapping("/create")
    public Role registerRole(@RequestBody Role role) {
        
        log.info("Creating new Role : {}",role.getRoleName());

        return roleService.saveRole(role.getRoleName());
        
    }

    @GetMapping("/all")
    public ResponseEntity<?> allRoles() {
        
        log.info("/GET : all roles");

        List<Role> roles = this.roleService.getAllRoles();
        
        if(roles == null){
            return ResponseEntity.badRequest().body("No role found.");
        }
        return ResponseEntity.ok().body(roles);
        
    }

}
