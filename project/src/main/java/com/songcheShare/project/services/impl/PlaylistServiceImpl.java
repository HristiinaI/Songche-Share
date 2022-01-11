package com.songcheShare.project.services.impl;

import com.songcheShare.project.dtos.PlaylistDto;
import com.songcheShare.project.entities.Playlist;
import com.songcheShare.project.entities.Song;
import com.songcheShare.project.repositories.PlaylistRepository;
import com.songcheShare.project.repositories.SongRepository;
import com.songcheShare.project.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepostiory;

    @Override
    public List<Playlist> list() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist save(PlaylistDto playlistDto) {
        Playlist playlist = new Playlist();

        playlist.setName(playlistDto.getName());
        playlist.setIsPublic(playlistDto.getIsPublic());

        playlist.setUser(playlistDto.getUser());

        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist findById(Long id) {
        if (playlistRepository.findById(id).isPresent()) {
            return playlistRepository.findById(id).get();
        }

        return null;
    }

    @Override
    public Playlist findByName(String name) {
        return playlistRepository.findByName(name);
    }

    @Override
    public List<Playlist> getAllPlaylistsPerUser(Long id) {
        return playlistRepository
                .findAllPlaylistsByUserId(id);
    }

    @Override
    public List<Playlist> getAllPublicPlaylistsPerUser(Long id) {
        List<Playlist> all = playlistRepository
                .findAllPlaylistsByUserId(id);

        return all.stream().filter(Playlist::getIsPublic).collect(Collectors.toList());
    }

    @Override
    public Playlist addSongToPlaylist(String songName, Long id, Long userId) {
        Song song = songRepostiory.findSongByName(songName);

        if (song != null) {
            if (playlistRepository.findById(id).isPresent()) {
                Playlist playlist = playlistRepository.findById(id).get();
                if (playlist.getUser().getId().equals(userId)) {
                    Set<Song> songs = playlist.getSongs();
                    songs.add(song);
                    playlist.setSongs(songs);
                    return playlistRepository.save(playlist);
                }
            }
        }

        return null;
    }
}
