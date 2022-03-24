package com.example.challengespring.repository;

import com.example.challengespring.model.Album;
import com.example.challengespring.model.AlbumTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumTagRepository extends JpaRepository<AlbumTag, Integer> {
    List<AlbumTag> findAlbumTagByAlbum(Album album);
}