package com.LANChat.dao;

import com.LANChat.util.ConnUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao基类
 * 增删改查
 * @author Administrator
 *
 */
public class BaseDao<T> {
	
	private QueryRunner runner = new QueryRunner();
	
	private Class<T> beanType = null;
	
	public BaseDao() {
		Class clazz = this.getClass();
		
		Class superclass = clazz.getSuperclass();
		
		Type type = clazz.getGenericSuperclass();
		
		if(type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type[] typeArguments = pt.getActualTypeArguments();
			Type realType = typeArguments[0];
			if(realType instanceof Class) {
				beanType = (Class<T>) realType;
			}
		}
		
	}
	
	public Integer insertWithId(String sql, Object ... param) {
		Integer id = null;
		
		//1.获取数据库连接
		Connection connection = ConnUtil.getConn();
		
		//2.获取PreparedStatement对象
		PreparedStatement ps = null;
		
		//3.获取ResultSet对象用来保存返回的自增ID的值
		ResultSet rs = null;
		
		try {
			//在获取PreparedStatement对象时，通过附加另外一个参数的方式将PreparedStatement对象
			//设置为返回自增主键的模式
			ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < param.length; i++) {
				ps.setObject(i+1, param[i]);
			}
			ps.execute();
			
			//自增的主键是以结果集形式返回的
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			//4.释放资源
			ConnUtil.close(connection);
			
			try {
				DbUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				DbUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	/**
	 * 执行批量更新操作
	 * @param sql
	 * @param params 执行批量操作时的SQL语句的参数，是二维数组类型
	 * 在非批量处理的情况下，SQL语句的参数是一维数组，对应SQL语句的一次执行
	 * 在批量处理的情况下，SQL语句的参数是二维数组，对应SQL语句的多次执行
	 * 		其中每一个一维数组和一条SQL语句是对应的
	 * 		第二维数组的每一个元素分别对应SQL语句中的一个参数
	 */
	public void batchUpdate(String sql, Object [] ... params ) {
		Connection connection = ConnUtil.getConn();
		try {
			runner.batch(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(connection);
	}
	
	/**
	 * 获取单一值的方法，声明的泛型参数是根据接收返回值的变量的类型传入的
	 * 如果执行的是COUNT()函数需要注意它返回的是Long包装类型
	 * @param sql
	 * @param params
	 * @return
	 */
	public <E> E getSingleValue(String sql, Object ... params) {
		E e = null;
		
		Connection connection = ConnUtil.getConn();
		try {
			e = (E) runner.query(connection, sql, new ScalarHandler(), params);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ConnUtil.close(connection);
		
		return e;
	}
	
	/**
	 * 查询数据库返回实体类对象的集合
	 * @param sql
	 * @param params
	 * @return实体类对象的集合
	 */
	public List<T> getBeanList(String sql, Object ... params) {
		
		Connection connection = ConnUtil.getConn();
		
		List<T> list = null;
		
		try {
			list = runner.query(connection, sql, new BeanListHandler<T>(beanType), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConnUtil.close(connection);
		
		return list;
		
	}
	
	/**
	 * 返回单一对象的查询方法
	 * @param sql
	 * @param params
	 * @return 将数据库查询结果封装得到的对象
	 */
	public T getBean(String sql, Object ... params) {
		T t = null;
		
		Connection connection = ConnUtil.getConn();
		try {
			t = runner.query(connection, sql, new BeanHandler<T>(beanType), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(connection);
		
		return t;
	}
	
	/**
	 * 执行增删改的通用方法
	 * @param sql
	 * @param params
	 */
	public void update(String sql, Object ... params) {
		
		Connection connection = ConnUtil.getConn();
		try {
			runner.update(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnUtil.close(connection);
		
	}

}
