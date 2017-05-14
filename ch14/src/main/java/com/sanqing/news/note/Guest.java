/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news.note;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.DateUtil;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NOTEGuest;
import com.sanqing.persistence.NOTEReply;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

public class Guest {

    public Guest() {
        sdbo = null;
    }

    public void insNote(String userName, int sex, String email, String qq, String url, String headTitle, String content,
                        String image) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into noteguest(userName,sex,email,qq,url,headTitle,content,image,noteTime) values(?,?,?,?,?,?,?,?,?);";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, CodeFilter.toHtml(userName));
            sdbo.setInt(2, sex);
            sdbo.setString(3, CodeFilter.toHtml(email));
            sdbo.setString(4, CodeFilter.toHtml(qq));
            sdbo.setString(5, CodeFilter.toHtml(url));
            sdbo.setString(6, CodeFilter.toHtml(headTitle));
            sdbo.setString(7, CodeFilter.toHtml(content));
            sdbo.setString(8, CodeFilter.toHtml(image));
            sdbo.setString(9, DateUtil.getNowDate());
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Guest insNote() " + e.getMessage());
            Debug.writeLog("Guest insNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public int sumNote() {
        int sum = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select count(noteId) as total from noteguest;";
        try {
            ResultSet rs = sdbo.executeQuery(sql);
            rs.next();
            sum = rs.getInt("total");
            rs.close();
        } catch (Exception e) {
            System.out.print("Guest sumNote() " + e.getMessage());
            Debug.writeLog("Guest sumNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public Iterator listNote() {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from noteguest order by noteTime desc;";
        try {
            ResultSet rs;
            NOTEGuest guestTable;
            for (rs = sdbo.executeQuery(sql); rs.next(); vector.add(guestTable)) {
                guestTable = new NOTEGuest();
                guestTable.setNoteId(rs.getInt("noteId"));
                guestTable.setUserName(rs.getString("userName"));
                guestTable.setSex(rs.getInt("sex"));
                guestTable.setEmail(rs.getString("email"));
                guestTable.setQq(rs.getString("qq"));
                guestTable.setUrl(rs.getString("url"));
                guestTable.setHeadTitle(rs.getString("headTitle"));
                guestTable.setContent(rs.getString("content"));
                guestTable.setImage(rs.getString("image"));
                guestTable.setNoteTime(rs.getString("noteTime"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.print("Guest listNote() " + e.getMessage());
            Debug.writeLog("Guest listNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public String sexStr(int sex) {
        String sexStr = "\u7537";
        if (sex == 1) {
            sexStr = "\u5973";
            return sexStr;
        } else {
            return sexStr;
        }
    }

    public boolean isReply(int noteId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isReply = false;
        String sql = "select count(replyId) as total from notereply where noteId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, noteId);
            ResultSet rs = sdbo.executeQuery();
            rs.next();
            int sum = rs.getInt("total");
            if (sum > 0)
                isReply = true;
            rs.close();
        } catch (Exception e) {
            System.out.print("Guest isReply() " + e.getMessage());
            Debug.writeLog("Guest isReply(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isReply;
    }

    public Iterator listReply(int noteId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select replyTime,content from notereply where noteId=?;";
        Vector vector = new Vector();
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, noteId);
            ResultSet rs;
            NOTEReply replyTable;
            for (rs = sdbo.executeQuery(); rs.next(); vector.add(replyTable)) {
                replyTable = new NOTEReply();
                replyTable.setReplyTime(rs.getString("replyTime"));
                replyTable.setContent(rs.getString("content"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.print("Guest listreply() " + e.getMessage());
            Debug.writeLog("Guest listreply(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 219 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method insNote
Couldn't resolve all exception handlers in method insNote
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumNote
Couldn't resolve all exception handlers in method sumNote
Overlapped try statements detected. Not all exception handlers will be resolved in the method listNote
Couldn't resolve all exception handlers in method listNote
Overlapped try statements detected. Not all exception handlers will be resolved in the method isReply
Couldn't resolve all exception handlers in method isReply
Overlapped try statements detected. Not all exception handlers will be resolved in the method listReply
Couldn't resolve all exception handlers in method listReply
	Exit status: 0
	Caught exceptions:
*/