package com.multivendor.marketplace.service.implement;



import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.Role;
import com.multivendor.marketplace.repository.RoleRepository;
import com.multivendor.marketplace.service.RoleService;



@Service
public class RoleServiceImp implements RoleService {

    
    private Logger log = LoggerFactory.getLogger(RoleServiceImp.class);

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role saveRole(String name) {
        
        log.info("Saving Role {} in database",name);

        String id = UUID.randomUUID().toString();

        Role role = new Role();
        
        role.setRoleId(id);
        role.setRoleName(name);

        return roleRepo.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
     log.info("fetching all rles form database");
        return this.roleRepo.findAll();
    }
    @Override
    public Role getRoleByName(String name) {
        
        log.info("Getting the Role {} from database",name);
        Role role = this.roleRepo.findByRoleName(name);
        return role;
    }

 

    
}
