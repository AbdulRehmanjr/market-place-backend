package com.multivendor.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.UserFollower;
import com.multivendor.marketplace.model.UserFollowing;

public interface UserFollowingRepository  extends JpaRepository<UserFollowing,String>{


    UserFollowing findByUserUserIdAndFollowingUserId(String userId,String followingId);

    List<UserFollowing> findAllByUserUserId(String userId);
    
}
