package com.sanqing.service;

import com.sanqing.dao.SubjectDAO;
import com.sanqing.dao.SubjectDAOImpl;
import com.sanqing.po.Subject;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDAO subjectDAO = new SubjectDAOImpl();

    public boolean saveSubject(Subject subject) {
        String subjectTile = subject.getSubjectTitle();
        if (subjectDAO.findSubjectByTitle(subjectTile) == null) { //如果该试题标题不存在，允许添加
            subjectDAO.addSubject(subject);
            return true;
        } else {
            return false;
        }
    }

    public PageResult querySubjectByPage(Page page) {
        page = PageUtil.createPage(page.getEveryPage(),
                subjectDAO.findSubjectCount(), page.getCurrentPage());//根据总记录数创建分页信息
        List<Subject> list = subjectDAO.findSubjectByPage(page);//通过分页信息取得试题
        PageResult result = new PageResult(page, list);//封装分页信息和记录信息，返回给调用处
        return result;
    }

    public Subject showSubjectParticular(int subjectID) {
        return subjectDAO.findSubjectByID(subjectID);
    }

    public void updateSubject(Subject subject) {
        subjectDAO.updateSubject(subject);
    }

    public void deleteSubject(int subjectID) {
        subjectDAO.deleteSubject(subjectID);
    }

    public PageResult likeQueryBySubjectTitle(String subjectTitle, Page page) {
        page = PageUtil.createPage(page.getEveryPage(),
                subjectDAO.findLinkQueryCount(subjectTitle), page.getCurrentPage());//根据总记录数创建分页信息
        List<Subject> list = subjectDAO.likeQueryByTitle(subjectTitle, page);//通过分页信息模糊查询试题
        PageResult result = new PageResult(page, list);//封装分页信息和记录信息，返回给调用处
        return result;
    }

    public List<Subject> randomFindSubject(int number) {
        return subjectDAO.randomFindSubject(number);
    }

    public int accountResult(List<Integer> subjectIDs,
                             List<String> studentAnswers) {
        int GeneralPoint = 0;//总分
        for (int i = 0; i < subjectIDs.size(); i++) {
            String rightAnswer = subjectDAO.
                    findSubjectByID(subjectIDs.get(i)).getSubjectAnswer();//得到正确答案，通过试题ID
            if (rightAnswer.equals(studentAnswers.get(i))) {
                GeneralPoint += 5;//加5分
            }
        }
        return GeneralPoint;
    }
}
