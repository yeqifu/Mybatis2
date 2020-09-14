package com.yeqifu.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yeqifu.mybatis.bean.Employee;
import com.yeqifu.mybatis.dao.EmployeeMapper;

public class MybatisTest {

	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//1.通过mybatis-config.xml（全局配置文件）创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	/**
	 * 1.通过mybatis-config.xml（全局配置文件）创建sqlSessionFactory对象
	 * 		mybatis-config.xml中有数据源的一些信息
	 * 2.sql映射文件：配置了每一个sql，以及sql的封装规则等
	 * 3.将sql映射文件注册进入mybatis的全局配置文件中
	 * 4.写代码：
	 * 		1）、根据全局配置文件获得sqlSessionFactory对象
	 * 		2）、使用sqlSession工厂获得sqlSession对象，并使用它来进行增删改查
	 * 				一个sqlSession就代表和数据库的一次会话，用完就关闭
	 * 		3）、使用sql的唯一标识来告诉MyBatis执行那个sql，sql都是保存在sql映射文件中的
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2.获取sqlSession实例，能直接执行已经映射的sql语句
		SqlSession openSession = sqlSessionFactory.openSession();
		//sql的唯一标识：statement Unique identifier matching the statement to use
		//执行sql要用的参数：parameter A parameter object to pass to the statement
		//第一个参数推荐使用名称空间+sql的唯一标识
		try {
			Object selectOne = openSession.selectOne("com.yeqifu.mybatis.bean.Employee.selectEmployee", 1);
			System.out.println(selectOne);
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test2() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			//获取接口的实现类对象
			//将接口与xml进行绑定，mybatis就会为这个接口创建一个代理对象，代理对象再去实现增删改查
			EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
			//接口式编程可以实现类型检查和更安全的参数检查
			Employee employee = employeeMapper.getEmployeeById(1);
			System.out.println(employeeMapper.getClass());
			System.out.println(employee);
		} finally {
			openSession.close();
		}
	}
}













