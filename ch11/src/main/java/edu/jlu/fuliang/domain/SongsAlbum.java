package edu.jlu.fuliang.domain;

import java.util.HashSet;
import java.util.Set;

public class SongsAlbum {
    private Long id;
    private String name;
    private Set<Song> songs = new HashSet<Song>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

}
