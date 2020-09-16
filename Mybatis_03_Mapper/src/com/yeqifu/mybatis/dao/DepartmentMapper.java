package com.yeqifu.mybatis.dao;

import com.yeqifu.mybatis.bean.Department;

public interface DepartmentMapper {

	/**
	 * 根据部门id查出部门
	 * @param id
	 * @return
	 */
	Department getDepartmentById(Integer id);
	
	/**
	 * 根据部门id查询出部门，以及该部门下的所有员工
	 * @param id
	 * @return
	 */
	Department getDepartmentAndEmpById(Integer id);
	
	
	
}
