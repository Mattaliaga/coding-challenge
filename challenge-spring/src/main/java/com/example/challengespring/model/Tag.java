package com.example.challengespring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="text", columnDefinition = "varchar(255)", nullable=false)
    private String text;

    @OneToMany(mappedBy = "tag")
    @JsonIgnore
    private Set<AlbumTag> albumTags;

    public Tag() {
    }

    public Tag(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Set<AlbumTag> getAlbumTags() {
        return albumTags;
    }
    public void setAlbumTags(Set<AlbumTag> albumTags) {
        this.albumTags = albumTags;
    }
}
