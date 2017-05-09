package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.SongManageService;
import edu.jlu.fuliang.domain.Song;
import org.apache.log4j.Logger;

import java.util.List;

public class ListSongByRegionAction extends ActionSupport {
    private List<Song> songs;
    private String region = "china";
    private SongManageService songManageService;
    private Logger logger = Logger.getLogger(ListSongByRegionAction.class);

    public String execute() throws Exception {
        songs = songManageService.findSongBySongerRegion(region);
        logger.info("*********************************songs size: " + songs.size());
        return SUCCESS;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSongManageService(SongManageService songManageService) {
        this.songManageService = songManageService;
    }
}
