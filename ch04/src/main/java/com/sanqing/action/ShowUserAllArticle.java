package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.fenye.Page;
import com.sanqing.fenye.Result;
import com.sanqing.po.Article;
import com.sanqing.service.ArticleService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowUserAllArticle extends ActionSupport {
    private ArticleService articleService;
    private int currentPage;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public String execute() throws Exception {
        if (username == null || "".equals(username)) {
            //首先要获得session
            Map session = ActionContext.getContext().getSession();
            //获得username
            username = (String) session.get("username");
        }

        //通过调用业务逻辑组件来完成查询
        Page page = new Page();
        page.setCurrentPage(this.getCurrentPage());
        page.setEveryPage(5);

        Result result = articleService.showUserArticleByPage(username, page);
        page = result.getPage();
        List<Article> all = result.getList();
        List critiqueCounts = new ArrayList();

        for (Article article : all) {
            critiqueCounts.add(articleService.getCritiqueCount(article.getId()));
        }

        //把查询到的结果保存在一个范围，request
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("all", all);
        request.setAttribute("page", page);
        request.setAttribute("critiqueCounts", critiqueCounts);
        return this.SUCCESS;
    }

}
