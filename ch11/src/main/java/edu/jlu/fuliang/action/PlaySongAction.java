package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.SongManageService;
import edu.jlu.fuliang.domain.CompositeSinger;
import edu.jlu.fuliang.domain.Singer;
import edu.jlu.fuliang.domain.SingleSinger;
import edu.jlu.fuliang.domain.Song;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlaySongAction extends ActionSupport {
    private Long[] songIds;
    private String songListFileName = "";
    private SongManageService songManageService;
    private String localAddr;
    private Map<Song, Set<SingleSinger>> songSingerMap = new HashMap<Song, Set<SingleSinger>>();
    private Logger logger = Logger.getLogger(PlaySongAction.class);

    public String execute() throws Exception {

        Song[] songs = new Song[songIds.length];
        for (int i = 0; i < songIds.length; i++) {
            songListFileName += songIds[i] + "_";
            songs[i] = songManageService.getSongById(songIds[i]);
        }
        generatePlayListFile(songs);
        generateSongSingerMap(songs);
        return SUCCESS;
    }

    private void generatePlayListFile(Song[] songs) throws Exception {
        ServletRequest request = ServletActionContext.getRequest();
        localAddr = "http://localhost:8181" + "/ch11";
        songListFileName += ".m3u";
        String songListLocation = request.getRealPath("/upload") + "/" + songListFileName;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(songListLocation)));

        for (int i = 0; i < songIds.length; i++) {
            String tempLocation = songs[i].getLocation();
            String songFileName = tempLocation.substring(tempLocation.lastIndexOf("/") + 1);
            out.println(songFileName);
        }
        out.close();
    }

    private void generateSongSingerMap(Song[] songs) {
        Set<SingleSinger> singers = new HashSet<SingleSinger>();
        Singer singer;
        for (int i = 0; i < songs.length; i++) {
            singers = new HashSet<SingleSinger>();
            singer = songs[i].getSinger();

            if (singer instanceof SingleSinger) {
                singers.add((SingleSinger) singer);
                logger.info("**********************************add singleSinger " + singer.getRegion());
            } else if (singer instanceof CompositeSinger) {
                CompositeSinger tempSinger = (CompositeSinger) singer;
                singers = tempSinger.getSingleSingers();
                logger.info("***********************************singers Size " + singers.size());
            }
            songSingerMap.put(songs[i], singers);
        }
    }

    public Map<Song, Set<SingleSinger>> getSongSingerMap() {
        return songSingerMap;
    }


    public void setSongSingerMap(Map<Song, Set<SingleSinger>> songSingerMap) {
        this.songSingerMap = songSingerMap;
    }


    public void setSongManageService(SongManageService songManageService) {
        this.songManageService = songManageService;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public Long[] getSongIds() {
        return songIds;
    }

    public void setSongIds(Long[] songIds) {
        this.songIds = songIds;
    }

    public String getSongListFileName() {
        return songListFileName;
    }

    public void setSongListFileName(String songListFileName) {
        this.songListFileName = songListFileName;
    }
}
