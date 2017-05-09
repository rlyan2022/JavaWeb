package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.SongManageService;
import edu.jlu.fuliang.domain.CompositeSinger;
import edu.jlu.fuliang.domain.Singer;
import edu.jlu.fuliang.domain.SingleSinger;
import edu.jlu.fuliang.domain.Song;
import org.apache.log4j.Logger;

import java.util.*;

public class SongAction extends ActionSupport {
    private Song song;
    private List<Song> songs;
    private Map<Song, Set<SingleSinger>> songSingerMap = new HashMap<Song, Set<SingleSinger>>();
    private Map<Song, Set<SingleSinger>> songSingerChinaMap = new HashMap<Song, Set<SingleSinger>>();
    private Map<Song, Set<SingleSinger>> songSingerKoreaMap = new HashMap<Song, Set<SingleSinger>>();
    private Map<Song, Set<SingleSinger>> songSingerJapanMap = new HashMap<Song, Set<SingleSinger>>();
    private Map<Song, Set<SingleSinger>> songSingerOccidentMap = new HashMap<Song, Set<SingleSinger>>();

    private SongManageService songManageService;
    Logger logger = Logger.getLogger(SongAction.class);

    public String listSong() {
        songs = songManageService.getAllSongs();
        Set<SingleSinger> singers = null;
        Singer singer = null;
        for (int i = 0; i < songs.size(); i++) {
            singers = new HashSet<SingleSinger>();
            singer = songs.get(i).getSinger();

            if (singer instanceof SingleSinger) {
                singers.add((SingleSinger) singer);
                logger.info("**********************************add singleSinger " + singer.getRegion());
            } else if (singer instanceof CompositeSinger) {
                CompositeSinger tempSinger = (CompositeSinger) singer;
                singers = tempSinger.getSingleSingers();
                logger.info("***********************************singers Size " + singers.size());
            }

            songSingerMap.put(songs.get(i), singers);
            logger.info("***********************************singer　Region " + singer.getRegion());
            if (singer.getRegion().equals("china")) {
                songSingerChinaMap.put(songs.get(i), singers);
            } else if (singer.getRegion().equals("korea")) {
                songSingerKoreaMap.put(songs.get(i), singers);
            } else if (singer.getRegion().equals("japan")) {
                songSingerJapanMap.put(songs.get(i), singers);
            } else {
                songSingerOccidentMap.put(songs.get(i), singers);
            }
        }
        return SUCCESS;
    }

    public Map<Song, Set<SingleSinger>> getSongSingerMap() {
        return songSingerMap;
    }

    public void setSongSingerMap(Map<Song, Set<SingleSinger>> songSingerMap) {
        this.songSingerMap = songSingerMap;
    }

    public Map<Song, Set<SingleSinger>> getSongSingerChinaMap() {
        return songSingerChinaMap;
    }

    public void setSongSingerChinaMap(
            Map<Song, Set<SingleSinger>> songSingerChinaMap) {
        this.songSingerChinaMap = songSingerChinaMap;
    }

    public Map<Song, Set<SingleSinger>> getSongSingerKoreaMap() {
        return songSingerKoreaMap;
    }

    public void setSongSingerKoreaMap(
            Map<Song, Set<SingleSinger>> songSingerKoreaMap) {
        this.songSingerKoreaMap = songSingerKoreaMap;
    }

    public Map<Song, Set<SingleSinger>> getSongSingerJapanMap() {
        return songSingerJapanMap;
    }

    public void setSongSingerJapanMap(
            Map<Song, Set<SingleSinger>> songSingerJapanMap) {
        this.songSingerJapanMap = songSingerJapanMap;
    }

    public String deleteSong() {
        songManageService.deleteSong(song.getId());
        return SUCCESS;
    }

    public String editSong() {
        songManageService.saveSong(song);
        return SUCCESS;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setSongManageService(SongManageService songManageService) {
        this.songManageService = songManageService;
    }

    public Map<Song, Set<SingleSinger>> getSongSingerOccidentMap() {
        return songSingerOccidentMap;
    }

    public void setSongSingerOccidentMap(
            Map<Song, Set<SingleSinger>> songSingerOccidentMap) {
        this.songSingerOccidentMap = songSingerOccidentMap;
    }
}
