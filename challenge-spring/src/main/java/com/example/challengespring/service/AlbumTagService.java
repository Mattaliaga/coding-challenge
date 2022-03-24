package com.example.challengespring.service;

import com.example.challengespring.model.Album;
import com.example.challengespring.model.AlbumTag;
import com.example.challengespring.repository.AlbumTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumTagService {
    @Autowired
    private AlbumTagRepository albumTagRepository;

    public List<AlbumTag> getAllAlbumTag() {
        return albumTagRepository.findAll();
    }

    public List<AlbumTag> getAllAlbumTagForAlbum(Album album) {
        return albumTagRepository.findAlbumTagByAlbum(album);
    }

    public void saveAlbumTag(AlbumTag albumTag) {
        albumTagRepository.save(albumTag);
    }
}
