package com.songcheShare.project.controllers;

// Inspect the Tomcat server and deliver here in controller

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.entities.WeekSong;
import com.songcheShare.project.repositories.SongRepository;
import com.songcheShare.project.repositories.WeekSongRepository;
import com.songcheShare.project.services.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//from where queries will come
//when spring security is set up this will be override auto
@CrossOrigin(origins = "*")
@RequestMapping("/song")
public class SongController {

    //"final", because after first value add to variable will not be change
    private final SongRepository songRepo;
    private SongService songService;
    private  final WeekSongRepository weekSongRepo;


    //in spring app we don't need "new" instance of class
    //"hibernate" does this job, and say to hibernate that we have "SongRepo" var in this controller
    public SongController(SongRepository songRepo, SongService songService, WeekSongRepository weekSongRepo) {
        this.songRepo = songRepo;
        this.songService = songService;
        this.weekSongRepo = weekSongRepo;
    }

    @GetMapping("/list")
    public Iterable<Song> getAllSongs(){
        return songService.list();
    }

    //find/name?name=Buttons
    @GetMapping("/find/name")
    public String findSongByName(String name){
      Optional<Song> song = Optional.ofNullable(songRepo.findSongByName(name));
      if(song.isEmpty()){
          return "No song found!";
      }
      return song.get().toString();
    }
    //findBySinger?singer=Jennifer#Lopez
    @GetMapping("/find/singer")
    public String findSongBySinger(String singer){
        Optional<Song> song = Optional.ofNullable(songRepo.findSongBySinger(singer));
        if(song.isEmpty()){
            return "No song found!";
        }
        return song.get().toString();
    }
    //findByGenre?genre=PopFolk
    @GetMapping("/find/genre")
    public String findSongByGenre(String genre){
        Optional<Song> song = Optional.ofNullable(songRepo.findSongByGenre(genre));
        if(song.isEmpty()){
            return "No song found!";
        }
        return song.get().toString();
    }

    @GetMapping("/weekSongs")
    public List<WeekSong> getAllWeekSongs(){
        return weekSongRepo.findAll();
    }

    @PostMapping("/addWeekSong")
    public WeekSong addWeekSong(Integer weekNumber, Song song){
        WeekSong weekSong = weekSongRepo.findWeekBySong(song)
                .orElse(new WeekSong(weekNumber, song));
        if(weekSong.getId() != null){
            weekSong.setWeek(weekNumber);
        }
        return weekSongRepo.save(weekSong);
    }



}
