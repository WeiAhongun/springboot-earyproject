package com.shangma.cn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Employee {

  private long employeeId;
  private String employeeName;
  private String employeeImg;
  private String employeeEmail;
  private String employeeDept;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private java.util.Date employeeTime;


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }


  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }


  public String getEmployeeImg() {
    return employeeImg;
  }

  public void setEmployeeImg(String employeeImg) {
    this.employeeImg = employeeImg;
  }


  public String getEmployeeEmail() {
    return employeeEmail;
  }

  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }


  public String getEmployeeDept() {
    return employeeDept;
  }

  public void setEmployeeDept(String employeeDept) {
    this.employeeDept = employeeDept;
  }


  public java.util.Date getEmployeeTime() {
    return employeeTime;
  }

  public void setEmployeeTime(java.util.Date employeeTime) {
    this.employeeTime = employeeTime;
  }

}
