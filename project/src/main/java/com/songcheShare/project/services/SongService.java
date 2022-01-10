package com.songcheShare.project.services;

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepo;

    public SongService(SongRepository songRepo){
        this.songRepo = songRepo;
    }

    public Iterable<Song> list(){
        return songRepo.findAll();
    }

    public Song save(Song song){
        return songRepo.save(song);
    }


}
