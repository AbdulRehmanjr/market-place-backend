package com.multivendor.marketplace.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Wardrobe> wardrobes = new HashSet<>();

    @OneToMany(mappedBy = "following")
    private Set<UserFollowing> followings = new HashSet<>();

    @OneToMany(mappedBy = "follower")
    private Set<UserFollower> followers = new HashSet<>();
    /*
     * @ManyToMany(fetch = FetchType.LAZY)
     * 
     * @JoinTable(name = "user_follows",
     * joinColumns = {@JoinColumn(name = "follower_id")},
     * inverseJoinColumns = {@JoinColumn(name = "following_id")})
     * 
     * @JsonIgnore
     * private Set<User> following = new HashSet<>();
     * 
     * @ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
     * private Set<User> followers = new HashSet<>();
     * 
     * public String getUserId() {
     * return userId;
     * }
     * 
     * public void setUserId(String userId) {
     * this.userId = userId;
     * }
     * 
     * public String getUserName() {
     * return userName;
     * }
     * 
     * public void setUserName(String userName) {
     * this.userName = userName;
     * }
     * 
     * public String getEmail() {
     * return email;
     * }
     * 
     * public void setEmail(String email) {
     * this.email = email;
     * }
     * 
     * public String getPassword() {
     * return password;
     * }
     * 
     * public void setPassword(String password) {
     * this.password = password;
     * }
     * 
     * public byte[] getProfilePicture() {
     * return profilePicture;
     * }
     * 
     * public void setProfilePicture(byte[] profilePicture) {
     * this.profilePicture = profilePicture;
     * }
     * 
     * public Role getRole() {
     * return role;
     * }
     * 
     * public void setRole(Role role) {
     * this.role = role;
     * }
     * 
     * public Set<Wardrobe> getWardrobes() {
     * return wardrobes;
     * }
     * 
     * public void setWardrobes(Set<Wardrobe> wardrobes) {
     * this.wardrobes = wardrobes;
     * }
     * 
     * public Set<User> getFollowing() {
     * return following;
     * }
     * 
     * public void setFollowing(Set<User> following) {
     * this.following = following;
     * }
     * 
     * public Set<User> getFollowers() {
     * return followers;
     * }
     * 
     * public void setFollowers(Set<User> followers) {
     * this.followers = followers;
     * }
     * 
     * @Override
     * public String toString() {
     * return "User [userId=" + userId + ", userName=" + userName + ", email=" +
     * email + ", password=" + password
     * + ", profilePicture=" + Arrays.toString(profilePicture) + ", role=" + role +
     * ", wardrobes=" + wardrobes
     * + ", following=" + following + ", followers=" + followers + "]";
     * }
     */

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
