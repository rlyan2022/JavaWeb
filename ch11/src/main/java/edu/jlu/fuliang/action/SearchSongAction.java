package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.SongManageService;
import edu.jlu.fuliang.domain.Song;

import java.util.List;

public class SearchSongAction extends ActionSupport {
    private List<Song> searchSongReult;
    private SongManageService songManageService;
    private String songName;

    public String execute() throws Exception {
        searchSongReult = songManageService.getSongByName(songName);
        return SUCCESS;
    }

    public List<Song> getSearchSongReult() {
        return searchSongReult;
    }

    public void setSearchSongReult(List<Song> searchSongReult) {
        this.searchSongReult = searchSongReult;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSongManageService(SongManageService songManageService) {
        this.songManageService = songManageService;
    }
}
