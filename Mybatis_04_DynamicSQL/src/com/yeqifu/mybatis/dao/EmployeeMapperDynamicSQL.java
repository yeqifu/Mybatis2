package com.yeqifu.mybatis.dao;

import java.util.List;

import com.yeqifu.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {

	/**
	 * 根据employee传入的条件进行查询
	 * @param employee
	 * @return
	 */
	List<Employee> getEmployeeByConditionIf(Employee employee);
	
	/**
	 * 根据employee传入的条件进行查询
	 * @param employee
	 * @return
	 */
	List<Employee> getEmployeeByConditionTrim(Employee employee);
	
	/**
	 * 根据employee传入的条件进行查询
	 * @param employee
	 * @return
	 */
	List<Employee> getEmployeeByConditionChoose(Employee employee);
}
