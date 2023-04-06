package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.UserFollower;

public interface UserFollowerService {

    void followerUser(String userId);

    void unfollowerUser(String userId);
    
    List<UserFollower> fetchAllFollowers();
}
