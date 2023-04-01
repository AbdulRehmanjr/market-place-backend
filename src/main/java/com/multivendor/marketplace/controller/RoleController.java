package com.multivendor.marketplace.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.service.RoleService;


@RestController
@RequestMapping("/role")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    

    @PostMapping("/create")
    public Role registerRole(@RequestBody Role role) {
        
        log.info("Creating new Role : {}",role.getRoleName());

        return roleService.saveRole(role.getRoleName());
        
    }

}
