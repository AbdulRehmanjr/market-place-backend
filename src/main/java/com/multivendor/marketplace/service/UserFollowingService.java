package com.multivendor.marketplace.service;

import java.util.List;

import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.model.UserFollowing;

public interface UserFollowingService  {
    void followUser(String userId,String followingId) throws Exception;

    void unfollowUser(String userId,String followingId);

    List<User> fetchAllFollowings(String userId);
}
