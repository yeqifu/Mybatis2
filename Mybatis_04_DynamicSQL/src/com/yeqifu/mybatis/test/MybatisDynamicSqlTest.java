package com.yeqifu.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yeqifu.mybatis.bean.Department;
import com.yeqifu.mybatis.bean.Employee;
import com.yeqifu.mybatis.dao.EmployeeMapperDynamicSQL;

public class MybatisDynamicSqlTest {

	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//1.通过mybatis-config.xml（全局配置文件）创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	/**
	 * 测试if
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			Employee employee = new Employee(null,"%o%",null,null);
			List<Employee> employees = mapper.getEmployeeByConditionIf(employee);
			for (Employee emp : employees) {
				System.out.println(emp);
			}
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试trim
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql2() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			Employee employee = new Employee(5,"%o%",null,null);
			List<Employee> employees = mapper.getEmployeeByConditionTrim(employee);
			for (Employee emp : employees) {
				System.out.println(emp);
			}
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试choose
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql3() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			Employee employee = new Employee(null,null,null,null);
			List<Employee> employees = mapper.getEmployeeByConditionChoose(employee);
			for (Employee emp : employees) {
				System.out.println(emp);
			}
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试set
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql5() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			Employee employee = new Employee(1,"luoyisss-",null,"luoyiss@gmailss.com");
			mapper.updateEmployeeById(employee);
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试foreach
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql6() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			List<Employee> employees = mapper.getEmployeeByConditionForeach(Arrays.asList(1,2,3,4));
			for (Employee employee : employees) {
				System.out.println(employee);
			}
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试foreach批量插入数据
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql7() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			List<Employee> employees = new ArrayList<>();
			employees.add(new Employee(null,"yechuqian","0","lusi@gmail.com",new Department(1,"开发部")));
			employees.add(new Employee(null,"yechucheng","0","jiucheng@gmail.com",new Department(2,"测试部")));
			mapper.addEmployes(employees);
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试bind标签
	 * @throws IOException
	 */
	@Test
	public void testDynamicSql8() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
		try {
			List<Employee> employees = mapper.getEmployeeBylastName("o");
			for (Employee employee : employees) {
				System.out.println(employee);
			}
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
}













