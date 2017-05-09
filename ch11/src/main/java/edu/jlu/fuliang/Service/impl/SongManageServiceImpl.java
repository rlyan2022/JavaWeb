package edu.jlu.fuliang.Service.impl;

import edu.jlu.fuliang.Service.SongManageService;
import edu.jlu.fuliang.dao.SongDAO;
import edu.jlu.fuliang.domain.Song;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

public class SongManageServiceImpl implements SongManageService {
    private SongDAO songDAO;


    public void deleteSong(long id) {
        songDAO.delete(id);
    }


    public List<Song> getAllSongs() {
        return songDAO.findAll();
    }


    public List<Song> getSongByName(String name) {
        return songDAO.findByName(name);
    }


    public void saveSong(Song song) {
        songDAO.save(song);
    }


    public void updateSong(Song song) {
        songDAO.save(song);
    }

    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }


    public Song getSongById(Long id) {
        return songDAO.findById(id);
    }


    public List<Song> findSongBySongerRegion(String region) {
        return songDAO.findBySingerRegion(region);
    }

    public static void main(String[] args) {
        Song song = new Song();
        song.setName("爱我别走");
        song.setLocation("c://hello.jdd");
        ApplicationContext context = new FileSystemXmlApplicationContext(new String[]{"src/applicationContext-db.xml",
                "src/applicationContext-dao.xml", "src/applicationContext-service.xml"});
        SongManageService service = (SongManageService) context.getBean("songManageService");
        //service.saveSong(song);
        /*List<Song> songs = service.getAllSongs();*/
        List<Song> songs = service.findSongBySongerRegion("china");
        for (Song s : songs) {
            System.out.println(s.getName());
        }
    }


}
