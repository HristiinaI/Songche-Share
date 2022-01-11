package com.songcheShare.project.controllers;

import com.songcheShare.project.dtos.PlaylistDto;
import com.songcheShare.project.entities.Playlist;
import com.songcheShare.project.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/list")
    public List<Playlist> getAllPlaylists() {
        return playlistService.list();
    }

    @GetMapping("/public-playlists")
    public List<Playlist> getAllPublicPlaylistsPerUser(@RequestParam("id") Long id) {
        return playlistService.getAllPublicPlaylistsPerUser(id);
    }

    @GetMapping("/show-playlists")
    public List<Playlist> getAllPlaylistsPerUser(@RequestParam("id") Long id) {
        return playlistService.getAllPlaylistsPerUser(id);
    }

    @PostMapping("/add-playlist")
    public ResponseEntity<String> addPlaylist(@RequestBody PlaylistDto playlistDto) {
        playlistService.save(playlistDto);
        return new ResponseEntity<>("Successfully created playlist!", HttpStatus.OK);
    }

    @PutMapping("/add-song")
    public Playlist addSongToPlaylist(@RequestParam("song_name") String songName,
                                                    @RequestParam("playlist_id") Long playlistId,
                                                    @RequestParam("user_id") Long userId) {
       return playlistService.addSongToPlaylist(songName, playlistId, userId);
    }
}
