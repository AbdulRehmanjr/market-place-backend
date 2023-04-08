package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.User;

public interface UserFollowingService  {

    void followUser(String currentId,String userId) ;

    void unfollowUser(String currentId,String userId);

    List<User> fetchAllFollowings(String userId);

    List<User> fetchAllFollowers(String userId);
}
