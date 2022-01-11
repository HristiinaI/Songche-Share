package com.songcheShare.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "weekSong")
public class WeekSong {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer week;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    public WeekSong(){

    }

    public WeekSong(Integer week, Song song){
        this.week = week;
        this.song = song;
    }

    public Long getId() {
        return id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
