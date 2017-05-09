package edu.jlu.fuliang.domain;


public class Song {
    private Long id;
    private String name;
    private String location;
    //private Lyric lyric;
    private Singer singer;
    //private Set<Category> categories = new HashSet<Category>();


    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
