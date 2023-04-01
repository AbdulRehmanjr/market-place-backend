package com.multivendor.marketplace.service;

import com.multivendor.marketplace.model.Role;

public interface RoleService {
    
    public Role saveRole(String name);

    public Role getRoleByName(String name);
}
