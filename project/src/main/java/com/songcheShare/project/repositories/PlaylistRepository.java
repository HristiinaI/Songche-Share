package com.songcheShare.project.repositories;

import com.songcheShare.project.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllPlaylistsByUserId(Long id);

    Playlist findByName(String name);
}
