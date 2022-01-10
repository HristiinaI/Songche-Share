package com.songcheShare.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private enum Genre {
        Pop,
        Rock,
        Rap,
        PopFolk,
    }

    @Column(name = "name")
    private String name;
    private String singer;
    private Integer playCount;
    private Genre genre;

    // hibernate will not work without empty constructor, because we have one constructor
    public Song() {
    }

    public Song(String name, String singer, Integer playCount, Genre genre) {
        this.name = name;
        this.singer = singer;
        this.playCount = playCount;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
