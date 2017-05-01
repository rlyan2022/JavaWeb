package com.sanqing.dao;

import com.sanqing.fenye.Page;
import com.sanqing.po.Article;

import java.util.List;

public interface ArticleDAO {
    //定义一个add方法，用来保存博客文章
    public void add(Article article);

    //定义一个queryUserAll,用来取出用户的所有文章
    public List<Article> queryUserAll(String username);

    //获得用户文章总记录数
    public int queryUserAllCount(String username);

    //按分页信息查询用户的记录
    public List<Article> queryByPage(String username, Page page);

    //获得所有文章的总记录数
    public int queryAllCount();

    //按分页信息查询记录
    public List<Article> queryAllByPage(Page page);

    //按ID查询文章
    public Article queryById(int id);
}
