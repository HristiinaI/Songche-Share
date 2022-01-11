package com.songcheShare.project.repositories;

import com.songcheShare.project.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// can generate SQl queries without write SQL - with keywords or JPQl
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findSongByName(String name);
    Song findSongBySinger(String singer);
    Song findSongByGenre(String genre);

//    @Query("SELECT s" +
//            "FROM Song s" +
//            "WHERE" +
//            "lower(s.name)" +
//            "LIKE :#{#name == null || #name.isEmpty()? '%' : #name+'%'}")
//    List<Song> filterSongs(String name);

}
