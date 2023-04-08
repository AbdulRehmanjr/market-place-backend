package com.multivendor.marketplace.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.model.UserFollower;
import com.multivendor.marketplace.model.UserFollowing;
import com.multivendor.marketplace.repository.UserFollowerRepository;
import com.multivendor.marketplace.repository.UserFollowingRepository;
import com.multivendor.marketplace.repository.UserRepository;
import com.multivendor.marketplace.service.UserFollowerService;
import com.multivendor.marketplace.service.UserFollowingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserFollowingServiceImpl implements UserFollowingService {

    @Autowired
    private UserFollowingRepository ufr;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserFollowerRepository ufrr;

    @Override
    public void followUser(String currentId,String userId)   {
        
        log.info("Adding the follower  in database");

        User currentUser = this.userRepo.findById(currentId).get();

        User followingUser = this.userRepo.findById(userId).get();

        if(currentUser==null || followingUser ==null){
            log.error("Error user or follower not avaible");
        }
        // {@current} user is follower of this
        UserFollower ufr = new UserFollower();
        // {@Current} user is following this 
        UserFollowing uf = new UserFollowing();
        
        String following = UUID.randomUUID().toString();
        uf.setId(following);
        uf.setUser(currentUser);
        uf.setFollowing(followingUser);
        
        String follower = UUID.randomUUID().toString();
        ufr.setId(follower);
        ufr.setFollower(currentUser);
        ufr.setUser(followingUser);
        this.ufrr.save(ufr);
        this.ufr.save(uf);  
    }

    @Override
    public void unfollowUser(String currentId,String userId) {
        log.info("Removing the follower from database");

        UserFollowing uf = this.ufr.findByUserUserIdAndFollowingUserId(userId, currentId);

        if(uf==null){
            log.error("Error in unfollow or user is already unfollow");
            return;
        }
        this.ufr.deleteById(uf.getId());
    }

    @Override
    public List<User> fetchAllFollowings(String userId) {

        List<User> users = new ArrayList<>();

        List<UserFollowing> uf = this.ufr.findAllByUserUserId(userId);

        if(uf==null){
            log.error("No user following Found");
            return null;
        }
        for(UserFollowing user : uf){
            users.add(user.getFollowing());
        }
        return users;
    }

    @Override
    public List<User> fetchAllFollowers(String userId) {
        List<User> users = new ArrayList<>();

        List<UserFollower> ufr = this.ufrr.findAllByUserUserId(userId);

        if(ufr==null){
            log.error("No user following Found");
            return null;
        }
        for(UserFollower user : ufr){
            users.add(user.getFollower());
        }
        return users;
    }
    
}
