package com.songcheShare.project.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followerId;

    private Long userId;
}
