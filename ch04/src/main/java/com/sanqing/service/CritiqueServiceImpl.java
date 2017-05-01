package com.sanqing.service;

import com.sanqing.dao.CritiqueDAO;
import com.sanqing.fenye.Page;
import com.sanqing.fenye.PageUtil;
import com.sanqing.fenye.Result;
import com.sanqing.po.Critique;

import java.util.List;

public class CritiqueServiceImpl implements CritiqueService {
    private CritiqueDAO critiqueDAO;

    public CritiqueDAO getCritiqueDAO() {
        return critiqueDAO;
    }

    public void setCritiqueDAO(CritiqueDAO critiqueDAO) {
        this.critiqueDAO = critiqueDAO;
    }

    public void addCritique(Critique critique) {
        critiqueDAO.addCritique(critique);
    }

    public Result showCritiqueByPage(int AId, Page page) {
        page = PageUtil.createPage(page, critiqueDAO.queryCritiqueCount(AId));
        List<Critique> all = critiqueDAO.queryByPage(AId, page);
        Result result = new Result();
        result.setPage(page);
        result.setList(all);
        return result;
    }

    public int getCritiqueCount(int AId) {
        return critiqueDAO.queryCritiqueCount(AId);
    }
}
