package com.yeqifu.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yeqifu.mybatis.bean.Employee;

public interface EmployeeMapperCache {
	
	/**
	 * 根据employee的id查询出employee
	 * @param id
	 * @return
	 */
	Employee getEmployeeById(Integer id);
	
	/**
	 * 添加一个employee
	 * @param employee
	 * @return
	 */
	Integer addEmployee(Employee employee);

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
	
	/**
	 * 根据employee的id更新employee
	 * @param id
	 * @return
	 */
	void updateEmployeeById(Employee employee);
	
	/**
	 * 根据传入的employee的id，查询出多个employee
	 * @param ids
	 * @return
	 */
	List<Employee> getEmployeeByConditionForeach(@Param("ids")List<Integer> ids);
	
	/**
	 * 批量插入employees
	 * @param employees
	 */
	void addEmployes(@Param("employees") List<Employee> employees);
	
	/**
	 * 根据用户的lastName查询出用户
	 * @param lastName
	 * @return
	 */
	List<Employee> getEmployeeBylastName(String lastName);
	
}
