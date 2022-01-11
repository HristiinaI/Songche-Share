package com.songcheShare.project.services.impl;

import com.songcheShare.project.dtos.FollowerDto;
import com.songcheShare.project.entities.Follower;
import com.songcheShare.project.repositories.FollowerRepository;
import com.songcheShare.project.repositories.UserRepository;
import com.songcheShare.project.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public boolean validateInfo(FollowerDto followerDto) {
        return followerDto.getFollowerId() != null
                && followerDto.getUserId() != null;
    }

    @Override
    public boolean usersExist(FollowerDto followerDto) {
        return userRepository.findById(followerDto.getFollowerId()).isPresent()
                && userRepository.findById(followerDto.getUserId()).isPresent();
    }

    @Override
    public boolean isFollowUnique(FollowerDto followerDto) {
        return followerRepository.findByUserIdAndFollowerId(followerDto.getUserId(), followerDto.getFollowerId()) == null;
    }

    @Override
    public boolean isEqual(FollowerDto followerDto) {
        return followerDto.getFollowerId().equals(followerDto.getUserId());
    }

    @Override
    public Follower save(FollowerDto followerDto) {
        Follower follower = new Follower();

        follower.setUserId(followerDto.getUserId());
        follower.setFollowerId(followerDto.getFollowerId());

        return followerRepository.save(follower);
    }

    @Override
    public List<String> listFollowers(Long id) {
        List<String> followersUsernames = new ArrayList<>();
        followerRepository
                .findAllByUserId(id)
                .forEach(follower ->
                        userRepository
                                .findById(follower
                                        .getFollowerId())
                                .ifPresent(user ->
                                        followersUsernames
                                                .add(user.getUsername())));
        return followersUsernames;
    }

    @Override
    public List<String> listFollowing(Long id) {
        List<String> followingUsernames = new ArrayList<>();
        followerRepository
                .findAllByFollowerId(id)
                .forEach(follower ->
                        userRepository
                                .findById(follower
                                        .getUserId())
                                        .ifPresent(user ->
                                                followingUsernames
                                                        .add(user.getUsername())));
        return followingUsernames;

    }

    @Override
    public void unfollow(FollowerDto followerDto) {
        Follower follower = followerRepository
                .findByUserIdAndFollowerId
                        (followerDto.getUserId(),
                                followerDto.getFollowerId());

        followerRepository.delete(follower);
    }
}
