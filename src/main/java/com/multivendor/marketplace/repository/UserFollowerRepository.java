package com.multivendor.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.UserFollower;

public interface UserFollowerRepository extends JpaRepository<UserFollower,String> {

}
