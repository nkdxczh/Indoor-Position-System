package org.nkcs.ips.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.dao.INodeDao;
import org.nkcs.ips.db.ConnectionManager;
import org.nkcs.ips.db.DBUtils;
import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;

public class NodeDaoImpl implements INodeDao {
	
	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;
	
	public NodeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public List<Node> selectAll() {
		List<Node> lstNode=new ArrayList<Node>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from node";
		Object[] params = new Object[] {  };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				Node node=new Node();
				node.setId(resultSet.getInt(1));
				node.setX(resultSet.getFloat(2));
				node.setY(resultSet.getFloat(3));
				// 返回结果
				lstNode.add(node);
			}
			return lstNode;
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
	public int add(Node node) {
		// TODO Auto-generated method stub
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();
		
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into node values(?,?,?)";
		Object[] params = new Object[] { node.getId(), node.getX(),node.getY() };
		// 步骤5：使用dbutils方法实现添加操作
		int affectedRows = this.dbUtils.execOthers(connection, strSQL, params);
		return affectedRows;
	}

}
