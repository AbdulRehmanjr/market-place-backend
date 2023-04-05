package com.multivendor.marketplace.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multivendor.marketplace.model.User;
import com.multivendor.marketplace.model.UserFollower;
import com.multivendor.marketplace.model.UserFollowing;
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

    @Override
    public void followUser(String userId,String followingId) throws Exception {
        
        log.info("Adding the follower  in database");

        User user = this.userRepo.findById(userId).get();

        User follow = this.userRepo.findById(followingId).get();

        if(user==null || follow ==null){
            log.error("Error user or follower not avaible");
            throw  new Exception("User or Follower not found.");
        }

        UserFollowing uf = new UserFollowing();
        
        String id = UUID.randomUUID().toString();
        uf.setId(id);
        uf.setUser(user);
        uf.setFollowing(follow);

        this.ufr.save(uf);
    }

    @Override
    public void unfollowUser(String userId,String followingId) {
        log.info("Removing the follower from database");

        UserFollowing uf = this.ufr.findByUserUserIdAndFollowingUserId(userId, followingId);

        if(uf==null){
            log.error("Error in unfollow or user is already unfollow");
            return;
        }
        this.ufr.deleteById(uf.getId());
    }

    @Override
    public List<User> fetchAllFollowers(String userId) {

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
    
}
