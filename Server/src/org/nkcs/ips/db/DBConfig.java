package org.nkcs.ips.db;

import java.io.IOException;
import java.util.Properties;

public class DBConfig {

	private static final Properties props = new Properties();
	static{
		try {
			props.load(DBConfig.class.getResourceAsStream("db.cfg.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static final String IP = props.getProperty("IP");
	public static final String PORT = props.getProperty("PORT");
	public static final String DBNAME = props.getProperty("DBNAME");
	public static final String ACCOUNT = props.getProperty("ACCOUNT");
	public static final String PASSWORD = props.getProperty("PASSWORD");
}
