package com.songcheShare.project.controllers;

// Inspect the Tomcat server and deliver here in controller

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.repositories.SongRepository;
import com.songcheShare.project.services.SongService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//from where queries will come
//when spring security is set up this will be override auto
@CrossOrigin(origins = "*")
@RequestMapping("/song")
public class SongController {

    //"final", because after first value add to variable will not be change
    private final SongRepository songRepo;
    private final SongService songService;


    //in spring app we don't need "new" instance of class
    //"hibernate" does this job, and say to hibernate that we have "SongRepo" var in this controller
    public SongController(SongRepository songRepo, SongService songService) {
        this.songRepo = songRepo;
        this.songService = songService;
    }

    @GetMapping("/list")
    public Iterable<Song> getAllSongs(){
        return songService.list();
    }

    @GetMapping("/find")
    public Song findSong(String name){
        return songRepo.findSongByName(name);
    }


}
