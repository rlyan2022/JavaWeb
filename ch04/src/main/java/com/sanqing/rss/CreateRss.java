package com.sanqing.rss;

import com.sanqing.po.Article;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateRss {
    public static void publishRss(List<Article> list, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);                            //文件输出流
            BufferedWriter bw = new BufferedWriter(fw);                            //缓冲流
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");            //写入头部声明
            bw.write("<rss version=\"2.0\" xmlns:atom=\"" +
                    "http://www.w3.org/2005/Atom\"\r\n");
            bw.write("xmlns:cf=\"http://www.microsoft.com/" +
                    "schemas/rss/core/2005\"\r\n");
            bw.write("xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\r\n");
            bw.write("xmlns:trackback=\"http://madskills.com/public/" +
                    "xml/rss/module/trackback/\"\r\n");
            bw.write("xmlns:wfw=\"http://wellformedweb.org/CommentAPI/\"\r\n");
            bw.write("xmlns:slash=\"http://purl.org/rss/1.0/modules/slash/\"\r\n>");
            bw.write("<channel>\r\n");                                                //频道信息
            bw.write("<title>求贤人才博客网</title>\r\n");                                //标题
            bw.write("<link>http://localhost:8480/ch04/login.jsp</link>\r\n");    //链接地址
            bw.write("<description>专注于软件人才的培养</description>\r\n");            //频道描述
            for (Article art : list) {
                bw.write("<item>\r\n");                                                //栏目
                bw.write("<title>" + art.getTitle() + "</title>\r\n");                //栏目标题
                bw.write("<link>http://localhost:8480/ch04/user/" +
                        "showArticle.action?id = " + art.getId() + "</link>\r\n");    //栏目链接地址
                bw.write("<description>" + art.getContent() + "</description>\r\n");//栏目描述
                bw.write("<author>" + art.getUsername() + "</author>\r\n");            //作者
                bw.write("<pubDate>" + art.getDate() + "</pubDate>\r\n");            //发布时间
                bw.write("</item>\r\n");
            }
            bw.write("</channel>\r\n");
            bw.write("</rss>\r\n");
            //关闭流
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
