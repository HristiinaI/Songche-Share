package com.songcheShare.project.repositories;

import com.songcheShare.project.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

// can generate SQl queries without write SQL - with keywords or JPQl
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findSongByName(String name);
}
