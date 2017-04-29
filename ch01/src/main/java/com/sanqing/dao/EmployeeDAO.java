package com.sanqing.dao;

import com.sanqing.bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    public void addEmployee(Employee employee);        //定义添加员工信息的方法

    public void updateEmployee(Employee employee);    //定义修改员工信息的方法

    public void deleteEmployee(int employeeID);        //定义删除员工信息的方法

    public List<Employee> findAllEmployee();        //定义查询所有员工信息的方法

    public Employee findEmployeeById(int employeeID);//定义按ID查询员工信息的方法
}
