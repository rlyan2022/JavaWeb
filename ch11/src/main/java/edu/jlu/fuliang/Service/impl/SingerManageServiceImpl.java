package edu.jlu.fuliang.Service.impl;

import edu.jlu.fuliang.Service.SingerManageService;
import edu.jlu.fuliang.dao.SingerDAO;
import edu.jlu.fuliang.domain.Band;
import edu.jlu.fuliang.domain.CompositeSinger;
import edu.jlu.fuliang.domain.Singer;
import edu.jlu.fuliang.domain.SingleSinger;

import java.util.List;

public class SingerManageServiceImpl implements SingerManageService {
    private SingerDAO singerDAO;

    public void deleteSinger(Long id) {
        singerDAO.deleteSinger(id);
    }


    public List<Band> getAllBands() {
        return singerDAO.getAllBands();
    }


    public List<CompositeSinger> getAllCompositeSingers() {
        return singerDAO.getAllCompositeSingers();
    }


    public List<Singer> getAllSingers() {
        return singerDAO.getAllSingers();
    }


    public List<SingleSinger> getAllSingleSingers() {
        return singerDAO.getAllSingleSingers();
    }


    public List<Band> getBandsByName(String name) {
        return singerDAO.getBandsByName(name);
    }


    public Singer getSingerById(Long id) {
        return singerDAO.getSingerById(id);
    }


    public List<Singer> getSingerByRegion(String region) {
        return singerDAO.getSingerByRegion(region);
    }


    public List<Singer> getSingersByName(String name) {
        return singerDAO.getSingersByName(name);
    }


    public List<SingleSinger> getSingleSingersByName(String name) {
        return singerDAO.getSingleSingersByName(name);
    }


    public void saveSinger(Singer singer) {
        singerDAO.saveSinger(singer);
    }


    public void updateSinger(Singer singer) {
        singerDAO.updateSinger(singer);
    }

    public SingerDAO getSingerDAO() {
        return singerDAO;
    }

    public void setSingerDAO(SingerDAO singerDAO) {
        this.singerDAO = singerDAO;
    }
}
