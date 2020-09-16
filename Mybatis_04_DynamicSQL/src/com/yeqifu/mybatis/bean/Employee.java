package com.yeqifu.mybatis.bean;

public class Employee {
	
	private Integer Id;
	private String lastName;
	private String gender;
	private String email;
	private Department department;
	
	public Employee() {
		super();
	}
	
	public Employee(Integer id, String lastName, String gender, String email) {
		super();
		Id = id;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}

	public Employee(Integer id, String lastName, String gender, String email,Department department) {
		super();
		Id = id;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.department = department;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email
				+"]";
	}
}
