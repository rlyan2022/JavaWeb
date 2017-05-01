package com.sanqing.service;

import com.sanqing.dao.DianjiliangDAO;
import com.sanqing.po.Dianjiliang;

import java.util.Date;

public class DianjiliangServiceImpl implements DianjiliangService {
    private DianjiliangDAO dianjiliangDAO;

    public DianjiliangDAO getDianjiliangDAO() {
        return dianjiliangDAO;
    }

    public void setDianjiliangDAO(DianjiliangDAO dianjiliangDAO) {
        this.dianjiliangDAO = dianjiliangDAO;
    }

    public boolean isVistor(int AId, String IP, Date time) {
        if (dianjiliangDAO.queryByAId(AId, IP, time).size() != 0) {
            System.out.println("该IP今天点击过了");
            //表示用户已经点击过了。
            return true;
        } else {
            System.out.println("该IP今天没有点击过");
            //表示用户没有点击过
            Dianjiliang djl = new Dianjiliang();
            djl.setAId(AId);
            djl.setIp(IP);
            djl.setTime(time);
            //保存记录
            dianjiliangDAO.addJilu(djl);
            return false;
        }
    }


}
