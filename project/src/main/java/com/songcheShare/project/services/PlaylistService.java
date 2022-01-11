package com.songcheShare.project.services;

import com.songcheShare.project.dtos.PlaylistDto;
import com.songcheShare.project.entities.Playlist;

import java.util.List;

public interface PlaylistService {
    List<Playlist> list();

    Playlist save(PlaylistDto playlistDto);

    Playlist findById(Long id);

    Playlist findByName(String name);

    List<Playlist> getAllPlaylistsPerUser(Long id);

    List<Playlist> getAllPublicPlaylistsPerUser(Long id);

    Playlist addSongToPlaylist(String songName, Long id, Long userId);
}
