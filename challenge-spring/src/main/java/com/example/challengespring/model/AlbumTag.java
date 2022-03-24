package com.example.challengespring.model;

import javax.persistence.*;

@Entity
@Table(name = "albumTag")
public class AlbumTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    Album album;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    Tag tag;

    public AlbumTag() {
    }

    public AlbumTag(Album album, Tag tag) {
        this.album = album;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
