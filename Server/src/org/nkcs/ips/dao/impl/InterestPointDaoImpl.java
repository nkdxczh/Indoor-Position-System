package org.nkcs.ips.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.dao.IInterestPointDao;
import org.nkcs.ips.db.ConnectionManager;
import org.nkcs.ips.db.DBUtils;
import org.nkcs.ips.po.InterestPoint;
import org.nkcs.ips.po.User;

public class InterestPointDaoImpl implements IInterestPointDao {
	
	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;
	
	public InterestPointDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}
	
	@Override
	public List<InterestPoint> selectByCreator(int id) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<InterestPoint> lstIP = new ArrayList<InterestPoint>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from interestpoint where creator=?";
		Object[] params = new Object[] { id };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				InterestPoint interestPoint = new InterestPoint();
				interestPoint.setId(resultSet.getInt(1));
				interestPoint.setName(resultSet.getString(2));
				interestPoint.setType(resultSet.getInt(3));
				interestPoint.setMaxMem(resultSet.getInt(4));
				interestPoint.setX(resultSet.getFloat(5));
				interestPoint.setY(resultSet.getFloat(6));
				interestPoint.setDescription(resultSet.getString(7));
				interestPoint.setEndTime(resultSet.getTimestamp(8));
				interestPoint.setImage(resultSet.getString(9));
				interestPoint.setCreator(resultSet.getInt(10));
				lstIP.add(interestPoint);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
		return lstIP;
	}
	
	@Override
	public List<InterestPoint> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<InterestPoint> lstIP = new ArrayList<InterestPoint>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from interestpoint";
		Object[] params = new Object[] { };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				InterestPoint interestPoint = new InterestPoint();
				interestPoint.setId(resultSet.getInt(1));
				interestPoint.setName(resultSet.getString(2));
				interestPoint.setType(resultSet.getInt(3));
				interestPoint.setMaxMem(resultSet.getInt(4));
				interestPoint.setX(resultSet.getFloat(5));
				interestPoint.setY(resultSet.getFloat(6));
				interestPoint.setDescription(resultSet.getString(7));
				interestPoint.setEndTime(resultSet.getTimestamp(8));
				interestPoint.setImage(resultSet.getString(9));
				interestPoint.setCreator(resultSet.getInt(10));
				lstIP.add(interestPoint);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
		return lstIP;
	}

}
