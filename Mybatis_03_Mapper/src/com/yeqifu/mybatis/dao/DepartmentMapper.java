package com.yeqifu.mybatis.dao;

import com.yeqifu.mybatis.bean.Department;

public interface DepartmentMapper {

	/**
	 * 根据部门id查出部门
	 * @param id
	 * @return
	 */
	Department getDepartmentById(Integer id);
	
}
