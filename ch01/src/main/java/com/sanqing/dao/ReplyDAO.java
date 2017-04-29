package com.sanqing.dao;

import com.sanqing.bean.Reply;
import com.sanqing.util.Page;

import java.util.List;

public interface ReplyDAO {
    public void addReplay(Reply replay);            //添加回复

    public List<Reply> findReplayByMsgID(int messageID, Page page);//按文章ID查找回复

    public int findCountByMsgID(int messageID);//查询消息回复记录数
}
