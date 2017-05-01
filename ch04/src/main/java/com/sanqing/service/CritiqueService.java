package com.sanqing.service;

import com.sanqing.fenye.Page;
import com.sanqing.fenye.Result;
import com.sanqing.po.Critique;

public interface CritiqueService {
    //添加评论
    public void addCritique(Critique critique);

    //分页显示评论
    public Result showCritiqueByPage(int AId, Page page);

    //获得指定文章的评论数
    public int getCritiqueCount(int AId);
}
