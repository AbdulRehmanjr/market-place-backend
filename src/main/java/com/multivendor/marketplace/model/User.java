package com.multivendor.marketplace.model;


import java.util.HashSet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import jakarta.persistence.Lob;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;


@Entity
@Table(name = "USERTABLE")
public class User {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String password;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Wardrobe> wardrobes = new HashSet<>();

    @OneToMany(mappedBy = "following")
    private Set<UserFollowing> followings = new HashSet<>();

    @OneToMany(mappedBy = "follower")
    private Set<UserFollower> followers = new HashSet<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Wardrobe> getWardrobes() {
        return wardrobes;
    }

    public void setWardrobes(Set<Wardrobe> wardrobes) {
        this.wardrobes = wardrobes;
    }
}
