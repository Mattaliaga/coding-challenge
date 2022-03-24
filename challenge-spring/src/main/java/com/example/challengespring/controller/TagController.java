package com.example.challengespring.controller;

import com.example.challengespring.model.Album;
import com.example.challengespring.model.AlbumTag;
import com.example.challengespring.model.Tag;
import com.example.challengespring.model.TagAdd;
import com.example.challengespring.service.AlbumService;
import com.example.challengespring.service.AlbumTagService;
import com.example.challengespring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    AlbumTagService albumTagService;

    @Autowired
    AlbumService albumService;

    @CrossOrigin
    @GetMapping("")
    public List<Tag> getAll() {
        return tagService.getAllTag();
    }

    @CrossOrigin
    @PostMapping("/")
    public void add(@RequestBody TagAdd tagAdd) {
        Tag newTag = tagService.saveTag(tagAdd.tag);

        for (Album album : tagAdd.albums) {
            albumTagService.saveAlbumTag(new AlbumTag(album, newTag));
        }
    }

    @CrossOrigin
    @GetMapping("/ofAlbum/{id}")
    public List<Tag> getAlbumTags(@PathVariable Integer id) {
        Album album = albumService.getAlbum(id);
        List<AlbumTag> albumTags = albumTagService.getAllAlbumTagForAlbum(album);

        List<Tag> tags = new ArrayList<>();

        for (AlbumTag albumTag : albumTags) {
            tags.add(albumTag.getTag());
        }

        return tags;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        tagService.deleteTag(id);
    }
}
