package edu.jlu.fuliang.dao;

import edu.jlu.fuliang.domain.Song;

import java.util.List;

public interface SongDAO {
    public List<Song> findAll();

    public List<Song> findByName(String name);

    public List<Song> findBySingerRegion(String region);

    public Song findById(long id);

    public void update(Song song);

    public void save(Song song);

    public void delete(long id);
}
