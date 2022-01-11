package com.songcheShare.project.services;

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Iterable<Song> list() {
        return songRepository.findAll();
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public Iterable<Song> save(List<Song> songs) {
        return songRepository.saveAll(songs);
    }
}
