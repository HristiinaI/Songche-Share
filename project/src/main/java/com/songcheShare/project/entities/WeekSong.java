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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public WeekSong(){

    }

    public WeekSong(Integer week, Song song, User user){
        this.week = week;
        this.song = song;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
