package com.songcheShare.project.services;

import com.songcheShare.project.dtos.FollowerDto;
import com.songcheShare.project.entities.Follower;

import java.util.List;

public interface FollowerService {
    boolean validateInfo(FollowerDto followerDto);

    boolean usersExist(FollowerDto followerDto);

    boolean isFollowUnique(FollowerDto followerDto);

    boolean isEqual(FollowerDto followerDto);

    Follower save(FollowerDto followerDto);

    List<String> listFollowers(Long id);

    List<String> listFollowing(Long id);

    void unfollow(FollowerDto followerDto);
}
