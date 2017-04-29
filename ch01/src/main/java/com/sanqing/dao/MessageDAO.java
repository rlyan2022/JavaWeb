package com.sanqing.dao;

import com.sanqing.bean.Message;
import com.sanqing.util.Page;

import java.util.List;

public interface MessageDAO {
    public void addMessage(Message message);        //定义添加消息的方法

    public void updateMessage(Message message);    //定义修改消息的方法

    public void deleteMessage(int messageID);        //定义删除消息的方法

    public List<Message> findAllMessagee(Page page);        //定义按分页信息查询所有消息的方法

    public Message findMessageById(int messageID);    //定义按ID查询消息的方法

    public int findAllCount();                    //定义查询消息记录数
}
