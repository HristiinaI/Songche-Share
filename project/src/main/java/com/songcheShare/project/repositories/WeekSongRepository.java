package com.songcheShare.project.repositories;

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.entities.WeekSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeekSongRepository extends JpaRepository<WeekSong, Long> {
    Optional<WeekSong> findWeekBySong(Song song);
}
