package com.yeqifu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yeqifu.mybatis.bean.Department;
import com.yeqifu.mybatis.bean.Employee;
import com.yeqifu.mybatis.dao.DepartmentMapper;
import com.yeqifu.mybatis.dao.EmployeeMapperPlus;

public class MyBatisPlusTest {
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//1.通过mybatis-config.xml（全局配置文件）创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	@Test
	public void test1() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmployeeById(1);
			System.out.println(employee);
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test3() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department department = mapper.getDepartmentById(2);
			System.out.println(department);
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test2() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee employeeAndDept = mapper.getEmployeeAndDept(2);
			System.out.println(employeeAndDept.getLastName());
			System.out.println(employeeAndDept.getDepartment());
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test5() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department department = mapper.getDepartmentAndEmpById(1);
			System.out.println(department);
			System.out.println(department.getEmployees());
		} finally {
			openSession.close();
		}
	
	}
	
}
