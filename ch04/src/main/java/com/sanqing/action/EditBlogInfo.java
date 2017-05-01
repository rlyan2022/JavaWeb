package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.BlogInfo;
import com.sanqing.service.BlogInfoService;

import java.util.Map;

public class EditBlogInfo extends ActionSupport {
    private String blogtitle;
    private String idiograph;
    private BlogInfoService blogInfoService;

    public BlogInfoService getBlogInfoService() {
        return blogInfoService;
    }

    public void setBlogInfoService(BlogInfoService blogInfoService) {
        this.blogInfoService = blogInfoService;
    }

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle;
    }

    public String getIdiograph() {
        return idiograph;
    }

    public void setIdiograph(String idiograph) {
        this.idiograph = idiograph;
    }

    public String execute() throws Exception {
//		//获得request
//		HttpServletRequest request = ServletActionContext.getRequest();
//		//获得session
//		HttpSession session =request.getSession();
//		//获得username
//		String username = (String) session.getAttribute("username");

        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("username");

        BlogInfo blogInfo = new BlogInfo();
        //设置用户名
        blogInfo.setUsername(username);
        //设置博客标题
        blogInfo.setBlogtitle(blogtitle);
        //设置个性签名
        blogInfo.setIdiograph(idiograph);
        //调用业务逻辑组件来完成设置
        blogInfoService.setBlogInfo(blogInfo);

        return this.SUCCESS;
    }

}
