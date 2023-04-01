package com.multivendor.marketplace.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.Role;



public interface RoleRepository extends JpaRepository<Role, String> {

    
    public Role findByRoleName(String role);
    
}
