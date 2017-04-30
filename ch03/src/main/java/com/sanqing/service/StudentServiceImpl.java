package com.sanqing.service;

import com.sanqing.dao.StudentDAO;
import com.sanqing.dao.StudentDAOImpl;
import com.sanqing.po.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = new StudentDAOImpl();

    public boolean allowLogin(String studentID, String password) {

        Student student = studentDAO.findByStudentID(studentID);
        if (student == null) {//判断是否存在该ID的学生
            return false;
        } else {
            if (password.equals(student.getPassword())) {//判断密码是否相同
                return true;
            } else {
                return false;
            }

        }
    }

    public Student getStudentInfo(String studentID) {
        return studentDAO.findByStudentID(studentID);
    }

    public void setStudentResult(String studentID, int result) {
        Student student = studentDAO.findByStudentID(studentID);//根据ID查找到该学生
        student.setResult(result);//设置其成绩
        studentDAO.updateStudent(student);//更新学生信息
    }

    public List<Student> getStudentByName(String studentName) {
        return studentDAO.findByStudentName(studentName);
    }

    public List<Student> getStudentByClass(String sclass) {
        return studentDAO.findByStudentClass(sclass);
    }
}
