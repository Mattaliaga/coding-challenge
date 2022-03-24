package com.example.challengespring.service;

import com.example.challengespring.model.Album;
import com.example.challengespring.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAllAlbum() {
        return albumRepository.findAll();
    }

    public void saveAlbum(Album album) {
        albumRepository.save(album);
    }

    public Album getAlbum(Integer id) {
        return albumRepository.findById(id).get();
    }

    public Album getAlbum(String spotifyId) {
        return albumRepository.findBySpotifyId(spotifyId);
    }

    public void deleteAlbum(Integer id) {
        albumRepository.deleteById(id);
    }
    public void deleteAlbum(String spotifyId) {
        albumRepository.deleteBySpotifyId(spotifyId);
    }
}
