package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.fenye.Page;
import com.sanqing.fenye.Result;
import com.sanqing.po.Article;
import com.sanqing.rss.CreateRss;
import com.sanqing.service.ArticleService;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class ShowRSS extends ActionSupport {
    private ArticleService articleService;

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public String execute() throws Exception {
        Page page = new Page();        //分页信息
        page.setCurrentPage(0);        //设置当前页为第一页
        page.setEveryPage(10);        //每页显示10条记录
        Result result = articleService.
                showArticleByPage(page);//通过调用业务逻辑组件来完成查询
        page = result.getPage();
        List<Article> all = result.getList();//获得文章结果集
        String filePath = ServletActionContext.
                getServletContext().getRealPath("/rss.xml");//设置订阅文件地址
        CreateRss.publishRss(all, filePath);//写入订阅文件
        return this.SUCCESS;
    }
}
