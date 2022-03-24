package com.example.challengespring.repository;

import com.example.challengespring.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    void deleteBySpotifyId(String spotifyId);
    Album findBySpotifyId(String spotifyId);
}
