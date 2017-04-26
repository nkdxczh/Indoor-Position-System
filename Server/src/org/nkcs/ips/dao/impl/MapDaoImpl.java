package org.nkcs.ips.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.dao.IMapDao;
import org.nkcs.ips.db.ConnectionManager;
import org.nkcs.ips.db.DBUtils;
import org.nkcs.ips.po.Map;

public class MapDaoImpl implements IMapDao {
	
	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;
	
	public MapDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public List<Map> selectAll() {
		List<Map> lstMap=new ArrayList<Map>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from map";
		Object[] params = new Object[] {  };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				Map map=new Map();
				map.setId(resultSet.getInt(1));
				map.setStartx(resultSet.getFloat(2));
				map.setStarty(resultSet.getFloat(3));
				map.setEndx(resultSet.getFloat(4));
				map.setEndy(resultSet.getFloat(5));
				map.setHeight(resultSet.getFloat(6));
				map.setColor(resultSet.getInt(7));
				// 返回结果
				lstMap.add(map);
			}
			return lstMap;
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
	public int add(Map map) {
		// TODO Auto-generated method stub
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();
		
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into map values(?,?,?,?,?,?,?)";
		Object[] params = new Object[] { map.getId(), map.getStartx(),map.getStarty(),map.getEndx(),map.getEndy(),map.getHeight(),map.getColor() };
		// 步骤5：使用dbutils方法实现添加操作
		int affectedRows = this.dbUtils.execOthers(connection, strSQL, params);
		return affectedRows;
	}

}
