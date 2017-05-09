package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.SingerManageService;
import edu.jlu.fuliang.domain.*;
import edu.jlu.fuliang.dto.SingerDto;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;


public class UploadSongAction extends ActionSupport {

    private SingerManageService singerManageService;
    private String songName;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;
    //private SingerDto[] singerDtos;
    private List<SingerDto> singerDtos;
    private int type;
    private String bandName;


    public String execute() throws Exception {
        String fileName = getUploadFileName();
        FileOutputStream fos = new FileOutputStream(getSavePath() + "/" + fileName);
        FileInputStream fis = new FileInputStream(getUpload());
        byte[] buffer = new byte[1024];

        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fis.close();
        fos.close();
        Song song = new Song();
        song.setName(songName);
        song.setLocation(savePath + "/" + fileName);

        Singer singer = assambleSinger(singerDtos);
        if (singer != null) {
            singer.addSong(song);
            singerManageService.saveSinger(singer);
        }

        return SUCCESS;
    }

    private Singer assambleSinger(List<SingerDto> singerDtos) {
        switch (type) {
            case 1:
                SingleSinger singleSinger = new SingleSinger();
                singleSinger.setSex(singerDtos.get(0).getSex());
                singleSinger.setName(singerDtos.get(0).getName());
                singleSinger.setRegion(singerDtos.get(0).getRegion());
                return singleSinger;
            case 2:
                CompositeSinger compositionSinger = new CompositeSinger();
                compositionSinger.setRegion(singerDtos.get(0).getRegion());
                for (int i = 0; i < singerDtos.size(); i++) {
                    SingleSinger sSinger;
                    List<SingleSinger> singleSingers = singerManageService.getSingleSingersByName(singerDtos.get(i).getName());
                    if (singleSingers.size() > 0) {
                        sSinger = singleSingers.get(0);
                    } else {
                        sSinger = new SingleSinger();
                        sSinger.setRegion(singerDtos.get(i).getRegion());
                        sSinger.setSex(singerDtos.get(i).getSex());
                        sSinger.setName(singerDtos.get(i).getName());
                    }
                    compositionSinger.addSinger(sSinger);
                }
                return compositionSinger;
            case 3:
                Band band = new Band();
                band.setName(bandName);
                band.setRegion(singerDtos.get(0).getRegion());
                for (int i = 0; i < singerDtos.size(); i++) {
                    SingleSinger sSinger;
                    List<SingleSinger> singleSingers = singerManageService.getSingleSingersByName(singerDtos.get(i).getName());
                    if (singleSingers.size() > 0) {
                        sSinger = singleSingers.get(0);
                    } else {
                        sSinger = new SingleSinger();
                        sSinger.setRegion(singerDtos.get(i).getRegion());
                        sSinger.setSex(singerDtos.get(i).getSex());
                        sSinger.setName(singerDtos.get(i).getName());
                    }
                    band.addSinger(sSinger);
                }
                return band;
        }
        return null;
    }


    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }


    public String getSongName() {
        return songName;
    }


    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        Date date = new Date();
        return date.getTime() + uploadFileName.substring(uploadFileName.lastIndexOf("."));
    }


    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getSavePath() {
        return ServletActionContext.getRequest().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public void setSingerDtos(List<SingerDto> singerDtos) {
        this.singerDtos = singerDtos;
    }

    public List<SingerDto> getSingerDtos() {
        return singerDtos;
    }

    public void setSingerManageService(SingerManageService singerManageService) {
        this.singerManageService = singerManageService;
    }
}
