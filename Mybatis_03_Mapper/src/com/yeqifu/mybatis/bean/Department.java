package com.yeqifu.mybatis.bean;

public class Department {

	private Integer id;
	private String departName;
	
	public Department() {
		super();
	}
	public Department(Integer id, String departName) {
		super();
		this.id = id;
		this.departName = departName;
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
	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + "]";
	}
}
