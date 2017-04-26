package org.nkcs.ips.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.dao.IMapDao;
import org.nkcs.ips.dao.INodeDao;
import org.nkcs.ips.dao.IPathDao;
import org.nkcs.ips.dao.ISignalDao;
import org.nkcs.ips.db.ConnectionManager;
import org.nkcs.ips.db.DBUtils;
import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;
import org.nkcs.ips.po.Path;
import org.nkcs.ips.po.Signal;

public class SignalDaoImpl implements ISignalDao {
	
	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;
	
	public SignalDaoImpl(){

		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public List<Signal> selectAll() {
		List<Signal> lstSignal=new ArrayList<Signal>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from signal";
		Object[] params = new Object[] {  };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				Signal signal=new Signal();
				signal.setId(resultSet.getInt(1));
				signal.setX(resultSet.getFloat(2));
				signal.setY(resultSet.getFloat(3));
				signal.setUUID(resultSet.getString(4));
				// 返回结果
				lstSignal.add(signal);
			}
			return lstSignal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
	}

}
