package com.shangma.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.shangma.cn.entity.Employee;
import com.shangma.cn.http.PageVo;
import com.shangma.cn.mapper.EmployeeMapper;
import com.shangma.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageVo getAllEmployee() {
        List<Employee> allEmployee = employeeMapper.getAllEmployee();
        PageInfo<Employee> pageInfo = new PageInfo<>(allEmployee);
        long total = pageInfo.getTotal();
        return new PageVo(total,allEmployee);
    }

    @Override
    public Employee getEmpBID(long id) {
        return employeeMapper.getEmpBID(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public void delEmpBID(long id) {
        employeeMapper.delEmpBID(id);
    }

    @Override
    public void updEmp(Employee employee) {
        employeeMapper.updEmp(employee);
    }
}
