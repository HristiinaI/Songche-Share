package com.songcheShare.project.dtos;

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.entities.User;
import lombok.Data;

import java.util.Set;

@Data
public class PlaylistDto {
    private String name;
    private Boolean isPublic;
    private User user;
    private Set<Song> songs;
}
