package com.yeqifu.mybatis.bean;

public class Employee {
	
	private Integer Id;
	private String lastName;
	private String gender;
	private String email;
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
	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + "]";
	}

}
