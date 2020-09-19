package com.yeqifu.mybatis.bean;

import java.util.List;

public class Department {

	private Integer id;
	private String departName;
	private List<Employee> employees;

	
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Department(Integer id, String departName) {
		super();
		this.id = id;
		this.departName = departName;
	}

	

	public Department(Integer id, String departName, List<Employee> employees) {
		super();
		this.id = id;
		this.departName = departName;
		this.employees = employees;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + "]";
	}
}
