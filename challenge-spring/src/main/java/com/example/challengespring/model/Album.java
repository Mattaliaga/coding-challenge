package com.example.challengespring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="spotifyId", columnDefinition = "varchar(255)", nullable=false, unique = true)
    private String spotifyId;

    @Column(name="favorite", columnDefinition = "boolean default false")
    private boolean favorite;

    @OneToMany(mappedBy = "album", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<AlbumTag> albumTags;

    public Album() {
    }

    public Album(int id) {
        this.id = id;
    }

    public Album(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public Album(int id, String spotifyId, boolean favorite) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSpotifyId() {
        return spotifyId;
    }
    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public boolean getFavourite() {
        return favorite;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Set<AlbumTag> getAlbumTags() {
        return albumTags;
    }
    public void setAlbumTags(Set<AlbumTag> albumTags) {
        this.albumTags = albumTags;
    }
}
