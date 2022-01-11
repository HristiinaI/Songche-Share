package com.songcheShare.project.repositories;

import com.songcheShare.project.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findAllByFollowerId(Long followerId);

    List<Follower> findAllByUserId(Long userId);

    Follower findByUserIdAndFollowerId(Long userId, Long followerId);
}
