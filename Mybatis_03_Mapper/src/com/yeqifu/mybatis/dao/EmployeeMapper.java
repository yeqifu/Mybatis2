package com.yeqifu.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.yeqifu.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	/**
	 * 将多条记录封装成一个map：map<Integer,Employee>  键：是这条记录的主键   值：是记录封装后的javabean
	 * @return
	 */
	//告诉mybatis封装这个map的时候，使用那个属性作为map的key
	@MapKey("lastName")
	Map<Integer, Employee> getEmployeeBylastNameLikeReturnMap(String lastName);
	
	/**
	 * 将一条记录作为map返回，key是列名，值就是对应的值
	 * @param id
	 * @return
	 */
	Map<String, Object> getEmployeeByIdReturnMap(Integer id);
	
	/**
	 * 根据lastName模糊查询employee
	 * @param lastName
	 * @return
	 */
	List<Employee> getEmployeeBylastName(String lastName);
	
	/**
	 * 查询一个Employee
	 * @param id
	 * @return
	 */
	Employee getEmployeeById(Integer id);
	
	/**
	 * 根据employee的id和lastName查询
	 * @param id
	 * @param lastName
	 * @return
	 */
	Employee getEmployeeByIdAndlastName(@Param("id")Integer id,@Param("lastName")String lastName);
	
	/**
	 * 根据Map查询一个employee
	 * @param map
	 * @return
	 */
	Employee getEmployeeByMap(Map<String, Object> map);
	
	/**
	 * 添加一个Employee
	 * @param employee
	 */
	Boolean addEmployee(Employee employee);
	
	/**
	 * 修改一个Emp
	 * @param employee
	 */
	void updateEmployee(Employee employee);
	
	/**
	 * 删除一个Employee
	 * @param integer
	 * @return
	 */
	Integer deleteEmployee(Integer integer);
}
