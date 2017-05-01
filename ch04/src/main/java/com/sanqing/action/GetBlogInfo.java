package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.BlogInfo;
import com.sanqing.service.BlogInfoService;

import java.util.Map;

public class GetBlogInfo extends ActionSupport {

    private BlogInfoService blogInfoService;

    public BlogInfoService getBlogInfoService() {
        return blogInfoService;
    }

    public void setBlogInfoService(BlogInfoService blogInfoService) {
        this.blogInfoService = blogInfoService;
    }

    public String execute() throws Exception {
        //获得usrname
        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("username");

        //通过业务逻辑组件来查询
        BlogInfo bloginfo = blogInfoService.getBlogInfo(username);

        if (bloginfo != null) {
            session.put("blogtitle", bloginfo.getBlogtitle());
            session.put("idiograph", bloginfo.getIdiograph());
        }
        return this.SUCCESS;
    }
}
