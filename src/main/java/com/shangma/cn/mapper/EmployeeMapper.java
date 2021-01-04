package com.shangma.cn.mapper;

import com.shangma.cn.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployeeMapper {

    @Select("select * from employee")
    List<Employee> getAllEmployee();

    @Select("select * from employee where employee_id = #{id}")
    Employee getEmpBID(long id);

    @Insert("insert into employee (employee_name,employee_img,employee_email,employee_dept,employee_time) values (#{employeeName},#{employeeImg},#{employeeEmail},#{employeeDept},#{employeeTime})")
    void addEmployee(Employee employee);

    @Delete("delete from employee where employee_id = #{id}")
    void delEmpBID(long id);

    @Update("update employee set employee_name = #{employeeName},employee_img = #{employeeImg},employee_email = #{employeeEmail},employee_dept = #{employeeDept},employee_time = #{employeeTime} where employee_id = #{employeeId}")
    void updEmp(Employee employee);
}
