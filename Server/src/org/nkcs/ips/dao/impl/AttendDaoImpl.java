package org.nkcs.ips.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.dao.IAttendDao;
import org.nkcs.ips.db.ConnectionManager;
import org.nkcs.ips.db.DBUtils;
import org.nkcs.ips.po.Attend;

public class AttendDaoImpl implements IAttendDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;
	
	public AttendDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}
	
	@Override
	public List<Attend> selectByPid(int id) {
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Attend> lstAttend = new ArrayList<Attend>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from attend where pid=?";
		Object[] params = new Object[] { id };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Attend attend = new Attend();
				attend.setId(resultSet.getInt(1));
				attend.setUid(resultSet.getInt(2));
				attend.setPid(resultSet.getInt(3));
				lstAttend.add(attend);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
		return lstAttend;
	}
	
	public List<Attend> selectAll() {
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Attend> lstAttend = new ArrayList<Attend>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from attend";
		Object[] params = new Object[] {  };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Attend attend = new Attend();
				attend.setId(resultSet.getInt(1));
				attend.setUid(resultSet.getInt(2));
				attend.setPid(resultSet.getInt(3));
				lstAttend.add(attend);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
		return lstAttend;
	}

}
