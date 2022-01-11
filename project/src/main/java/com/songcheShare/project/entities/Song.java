package com.songcheShare.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private enum Genre {
        Pop,
        Rock,
        Rap,
        PopFolk,
    }

    @Column(name = "name")
    private String name;

    @Column()
    private String singer;

    @Column()
    private Integer playCount;

    @Column()
    private Genre genre;

    @JsonIgnore
    @OneToMany(mappedBy = "song")
    private Set<WeekSong> weekSongs;



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

    public Set<WeekSong> getWeekSongs() {
        return weekSongs;
    }

    public void setWeekSongs(Set<WeekSong> weekSongs) {
        this.weekSongs = weekSongs;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", playCount=" + playCount +
                ", genre=" + genre +
                '}';
    }
}
