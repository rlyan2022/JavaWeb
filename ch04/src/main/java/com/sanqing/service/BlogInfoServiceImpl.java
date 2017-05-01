package com.sanqing.service;

import com.sanqing.dao.BlogInfoDAO;
import com.sanqing.po.BlogInfo;

public class BlogInfoServiceImpl implements BlogInfoService {
    private BlogInfoDAO blogInfoDAO;

    public BlogInfo getBlogInfo(String username) {
        return blogInfoDAO.get(username);
    }

    public BlogInfoDAO getBlogInfoDAO() {
        return blogInfoDAO;
    }

    public void setBlogInfoDAO(BlogInfoDAO blogInfoDAO) {
        this.blogInfoDAO = blogInfoDAO;
    }

    public void setBlogInfo(BlogInfo blogInfo) {
        //通过调用DAO组件来完成
        blogInfoDAO.save(blogInfo);
    }

}
