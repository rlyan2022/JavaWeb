package com.sanqing.dao;

import com.sanqing.po.Dianjiliang;

import java.util.Date;
import java.util.List;

public interface DianjiliangDAO {

    //返回该文章，该IP，该时间的访问记录
    public List queryByAId(int AId, String IP, Date time);

    //添加访问记录
    public void addJilu(Dianjiliang dianjiliang);
}
