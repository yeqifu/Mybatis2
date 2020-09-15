package com.yeqifu.mybatis.dao;

import com.yeqifu.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

	/**
	 * 根据id查employee
	 * @param id
	 * @return
	 */
	Employee getEmployeeById(Integer id);
	
	/**
	 * 根据员工id查询出此员工以及该员工所属部门
	 * @param id
	 * @return
	 */
	Employee getEmployeeAndDept(Integer id);
	
}
