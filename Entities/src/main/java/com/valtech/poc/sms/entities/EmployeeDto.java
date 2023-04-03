package com.valtech.poc.sms.entities;

public class EmployeeDto {
	
	private String empName;
	private String phNum;
	private String mailId;
	private int empId;
	private String pass;
	private String role;
	private String managerName;
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDto(String empName, String phNum, String mailId, int empId, String pass, String role,
			String managerName) {
		super();
		this.empName = empName;
		this.phNum = phNum;
		this.mailId = mailId;
		this.empId = empId;
		this.pass = pass;
		this.role = role;
		this.managerName = managerName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPhNum() {
		return phNum;
	}
	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Override
	public String toString() {
		return "EmployeeDto [empName=" + empName + ", phNum=" + phNum + ", mailId=" + mailId + ", empId=" + empId
				+ ", pass=" + pass + ", role=" + role + ", managerName=" + managerName + "]";
	}
	
	
	
	
	

	

}
