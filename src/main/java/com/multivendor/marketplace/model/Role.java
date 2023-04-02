package com.multivendor.marketplace.model;



import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;




@Entity
@Table(name = "ROLETABLE")
public class Role implements GrantedAuthority{

    @Id
    private String roleId;
    private String roleName;
    
    @JsonIgnore
    @Transient
    @OneToMany
    private Set<User> users = new HashSet<>();

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
    }
    @Override
    public String getAuthority() {
        
        return this.getRoleName();
    }

}