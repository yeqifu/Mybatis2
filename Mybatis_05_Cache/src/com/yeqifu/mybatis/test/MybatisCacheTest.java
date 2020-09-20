package com.yeqifu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yeqifu.mybatis.bean.Employee;
import com.yeqifu.mybatis.dao.EmployeeMapperCache;

public class MybatisCacheTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//1.通过mybatis-config.xml（全局配置文件）创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	/**
	 * 两级缓存：
	 * 一级缓存：（本地缓存）sqlSession级别的缓存，一级缓存是一直开启的；sqlSession级别的一个map
	 * 			与数据库同一次会话期间查询到的数据会放在本地缓存中
	 * 			以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库
	 * 
	 * 			一级缓存失效的情况（没有使用当前一级缓存的情况，效果就是还需要再向数据库发出查询）			
	 * 			1、sqlSession不同
	 * 			2、sqlSession相同，查询条件不同 （当前一级缓存中还没有这个数据）
	 * 			3、sqlSession相同，两次查询之间执行了增删改操作 （这次增删改可能对当前数据有影响）
	 * 二级缓存：（全局缓存）基于namespace级别的缓存：一个namespace对应一个二级缓存：
	 * 		工作机制：
	 * 		1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
	 * 		2、如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容；
	 * 		3、sqlSession===EmployeeMapper==>Employee
	 * 						DepartmentMapper===>Department
	 * 			不同namespace查出的数据会放在自己对应的缓存中（map）
	 * 			效果：数据会从二级缓存中获取
	 * 				查出的数据都会被默认先放在一级缓存中。
	 * 				只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中
	 * 		使用：
	 * 			1）、开启全局二级缓存配置：<setting name="cacheEnabled" value="true"/>
	 * 			2）、去mapper.xml中配置使用二级缓存：
	 * 				<cache></cache>
	 * 			3）、我们的POJO需要实现序列化接口
	 */
	
	/**
	 * 测试一级缓存
	 * @throws IOException
	 */
	@Test
	public void testFirstLevelCache() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperCache mapper = openSession.getMapper(EmployeeMapperCache.class);
		try {
			Employee employee1 = mapper.getEmployeeById(1);
			System.out.println(employee1);
			Employee employee2 = mapper.getEmployeeById(1);
			System.out.println(employee2);
			System.out.println(employee1==employee2);
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 测试sqlSession不同，一级缓存失效的情况
	 * @throws IOException
	 */
	@Test
	public void testFirstLevelCache2() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession1 = sqlSessionFactory.openSession();
		EmployeeMapperCache mapper1 = openSession1.getMapper(EmployeeMapperCache.class);
		try {
			Employee employee1 = mapper1.getEmployeeById(1);
			System.out.println(employee1);
			//第二个openSession
			SqlSession openSession2 = sqlSessionFactory.openSession();
			EmployeeMapperCache mapper2 = openSession2.getMapper(EmployeeMapperCache.class);
			Employee employee2 = mapper2.getEmployeeById(1);
			System.out.println(employee2);
			System.out.println(employee1==employee2);
			openSession2.close();
		} finally {
			openSession1.close();
		}
	}
	
	/**
	 * sqlSession相同，两次查询之间执行了增删改操作 （这次增删改可能对当前数据有影响）
	 * @throws IOException
	 */
	@Test
	public void testFirstLevelCache3() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperCache mapper = openSession.getMapper(EmployeeMapperCache.class);
		try {
			Employee employee1 = mapper.getEmployeeById(1);
			System.out.println(employee1);
			Integer result = mapper.addEmployee(new Employee(null, "testCache","0", "cache@gamil.com",null));
			if (result!=0) {
				System.out.println("添加一条数据成功");
			}
			Employee employee2 = mapper.getEmployeeById(1);
			System.out.println(employee2);
			System.out.println(employee1==employee2);
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * sqlSession相同，手动清除了一级缓存
	 * @throws IOException
	 */
	@Test
	public void testFirstLevelCache5() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		EmployeeMapperCache mapper = openSession.getMapper(EmployeeMapperCache.class);
		try {
			Employee employee1 = mapper.getEmployeeById(1);
			System.out.println(employee1);
			//手动清除一级缓存
			openSession.clearCache();
			Employee employee2 = mapper.getEmployeeById(1);
			System.out.println(employee2);
			System.out.println(employee1==employee2);
		} finally {
			openSession.close();
		}
	}
	
	
	 /**
	  * 测试二级缓存
	  * @throws IOException 
	  */
	@Test
	public void testSecondLevelCache() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession1 = sqlSessionFactory.openSession();
		SqlSession openSession2 = sqlSessionFactory.openSession();
		try {
			EmployeeMapperCache mapper1 = openSession1.getMapper(EmployeeMapperCache.class);
			Employee employee1 = mapper1.getEmployeeById(1);
			System.out.println(employee1);
			openSession1.close();
			
			EmployeeMapperCache mapper2 = openSession2.getMapper(EmployeeMapperCache.class);
			Employee employee2 = mapper2.getEmployeeById(1);
			System.out.println(employee2);
			openSession2.close();
		} finally {
			// TODO: handle finally clause
		}
	}
	
}


















