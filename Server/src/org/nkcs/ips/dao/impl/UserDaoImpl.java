package org.nkcs.ips.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.dao.IUserDao;
import org.nkcs.ips.db.ConnectionManager;
import org.nkcs.ips.db.DBUtils;
import org.nkcs.ips.po.User;

public class UserDaoImpl implements IUserDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public UserDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public User selectById(int id) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		User user = new User();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from user where id=?";
		Object[] params = new Object[] { id };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			resultSet.next();
			// 步骤5-1：创建一个对象
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setX(resultSet.getFloat(3));
			user.setY(resultSet.getFloat(4));
			user.setPassword(resultSet.getString(5));
			user.setEmail(resultSet.getString(6));
			user.setType(resultSet.getInt(7));
			user.setImage(resultSet.getString(8));
			user.setShare(resultSet.getInt(9));
			// 返回结果
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
	}

	@Override
	public User selectByEmail(String email) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		User user = new User();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from user where email=?";
		Object[] params = new Object[] { email };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			resultSet.next();
			// 步骤5-1：创建一个对象
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setX(resultSet.getFloat(3));
			user.setY(resultSet.getFloat(4));
			user.setPassword(resultSet.getString(5));
			user.setEmail(resultSet.getString(6));
			user.setType(resultSet.getInt(7));
			user.setImage(resultSet.getString(8));
			user.setShare(resultSet.getInt(9));
			// 返回结果
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<User> lstUser=new ArrayList<User>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from user";
		Object[] params = new Object[] {};
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setX(resultSet.getFloat(3));
				user.setY(resultSet.getFloat(4));
				user.setPassword(resultSet.getString(5));
				user.setEmail(resultSet.getString(6));
				user.setType(resultSet.getInt(7));
				user.setImage(resultSet.getString(8));
				user.setShare(resultSet.getInt(9));
				lstUser.add(user);
			}
			
			// 返回结果
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int adapt(User user) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update user set id=?, name=?, x=?, y=?, password=?";
		Object[] params = new Object[] { user.getId(), user.getName(),
				user.getX(), user.getY(), user.getPassword() };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务行数
		return affectedRwos;
	}

	public float getNormal() {
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "select count(id) from user group by type";
		Object[] params = new Object[] {};
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		int i = 1, j = 0;
		try {
			resultSet.next();
			i = resultSet.getInt(1);
			resultSet.next();
			j = resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 步骤5：根据步骤4的操作结果提交或回滚事务行数
		return ((float) j / (float) (i + j)) * 100;
	}

}
