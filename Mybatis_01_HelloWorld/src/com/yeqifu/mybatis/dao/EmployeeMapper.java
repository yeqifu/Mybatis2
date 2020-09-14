package com.yeqifu.mybatis.dao;

import com.yeqifu.mybatis.bean.Employee;

public interface EmployeeMapper {
	Employee getEmployeeById(Integer id);
}
