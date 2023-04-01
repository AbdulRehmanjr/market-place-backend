package com.multivendor.marketplace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="USERTABLE")
public class User {
    
    @Id
    private String userId;
    private String userName;
    private String email;
    private String userPassword;
    
    @ManyToOne
    private Role role;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String randomUserId) {
        this.userId = randomUserId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", userPassword="
                + userPassword + ", role=" + role + "]";
    }

   
   

}
