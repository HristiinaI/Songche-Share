package com.songcheShare.project.repositories;

import com.songcheShare.project.entities.Song;
import com.songcheShare.project.entities.User;
import com.songcheShare.project.entities.WeekSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeekSongRepository extends JpaRepository<WeekSong, Long> {
    List<WeekSong> findWeekBySong(Song song);
    List<WeekSong> findWeekByWeek(Integer week);
    List<WeekSong> findWeekSongByUser(User user);

}
