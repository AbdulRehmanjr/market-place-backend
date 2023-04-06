package com.multivendor.marketplace.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.UserFollower;
import com.multivendor.marketplace.repository.UserFollowerRepository;
import com.multivendor.marketplace.service.UserFollowerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserFollowerServiceImpl implements UserFollowerService {

    @Autowired
    private UserFollowerRepository ufr;

  
    @Override
    public void followerUser(String userId) {
        log.info("Adding the follower list of {}",userId);
    }

    @Override
    public void unfollowerUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unfollowerUser'");
    }

    @Override
    public List<UserFollower> fetchAllFollowers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchAllFollowers'");
    }
    
}
