package com.multivendor.marketplace.service;

public interface UserFollowerService {

    void followUser(String userId);

    void unfollowUser(String userId);
    
}
