package com.multivendor.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multivendor.marketplace.model.User;

public interface UserRepository extends JpaRepository<User, String> {


    public List<User> findByUserName(String userName);

    public User findByEmail(String email);

}
