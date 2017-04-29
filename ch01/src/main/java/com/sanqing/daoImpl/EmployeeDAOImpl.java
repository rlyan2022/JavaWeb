package com.sanqing.daoImpl;

import com.sanqing.bean.Employee;
import com.sanqing.dao.EmployeeDAO;
import com.sanqing.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    public void addEmployee(Employee employee) {

    }

    public void deleteEmployee(int employeeID) {

    }

    public List<Employee> findAllEmployee() {
        return null;
    }

    public Employee findEmployeeById(int employeeID) {
        Connection conn = DBConnection.getConnection();        //获得连接对象
        String findByIDSQL = "select * from " +
                "tb_employee where employeeID = ?";    //SQL语句
        PreparedStatement pstmt = null;    //声明预处理对象
        ResultSet rs = null;
        Employee employee = null;
        try {
            pstmt = conn.prepareStatement(findByIDSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, employeeID);                    //设置参数
            rs = pstmt.executeQuery();                        //执行查询
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeID(rs.getInt(1));        //设置员工编号
                employee.setEmployeeName(rs.getString(2));    //设置员工姓名
                employee.setEmployeeSex(rs.getBoolean(3));    //设置员工性别
                employee.setEmployeeBirth(rs.getDate(4));    //设置出生日期
                employee.setEmployeePhone(rs.getString(5));    //设置办公室电话
                employee.setEmployeePlace(rs.getString(6));    //设置住址
                employee.setJoinTime(rs.getDate(7));        //设置录入时间
                employee.setPassword(rs.getString(8));        //设置系统口令
                employee.setLead(rs.getBoolean(9));            //设置是否为管理层领导
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return employee;
    }

    public void updateEmployee(Employee employee) {

    }

}
