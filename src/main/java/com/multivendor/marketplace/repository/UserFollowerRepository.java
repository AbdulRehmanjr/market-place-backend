package com.multivendor.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.UserFollower;

public interface UserFollowerRepository extends JpaRepository<UserFollower,String> {
    UserFollower findByUserUserIdAndFollowerUserId(String userId,String followerId);

    List<UserFollower> findAllByUserUserId(String userId);
}
