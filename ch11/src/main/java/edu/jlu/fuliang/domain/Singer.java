package edu.jlu.fuliang.domain;

import java.util.HashSet;
import java.util.Set;

public class Singer {
    private String region;
    private Long id;
    private Set<Song> songs = new HashSet<Song>();

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        songs.add(song);
        song.setSinger(this);
    }
}
