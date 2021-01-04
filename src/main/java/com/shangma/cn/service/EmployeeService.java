package com.shangma.cn.service;

import com.shangma.cn.entity.Employee;
import com.shangma.cn.http.PageVo;

import java.util.List;

public interface EmployeeService {

    PageVo getAllEmployee();


    Employee getEmpBID(long id);

    void addEmployee(Employee employee);

    void delEmpBID(long id);

    void updEmp(Employee employee);
}
