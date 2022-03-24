package com.example.challengespring.controller;

import com.example.challengespring.model.Album;
import com.example.challengespring.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @CrossOrigin
    @GetMapping("")
    public List<Album> getAll() {
        return albumService.getAllAlbum();
    }

    @CrossOrigin
    @PostMapping("/")
    public void add(@RequestBody Album album) {
        albumService.saveAlbum(album);
    }

    @CrossOrigin
    @PutMapping("/{favourite}")
    public void updateFavorite(@RequestBody Album album, @PathVariable String favourite) {
        album.setFavorite(favourite.equals("true"));
        albumService.saveAlbum(album);
    }

    @CrossOrigin
    @DeleteMapping("/{spotifyId}")
    public void delete(@PathVariable String spotifyId) {
        albumService.deleteAlbum(spotifyId);
    }
}
