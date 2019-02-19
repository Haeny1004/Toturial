package kr.sys4u.employee;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataBaseConnectionPool {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:HAENY";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";

	private boolean initialized = false;
	private ComboPooledDataSource cpds;
	
	public DataBaseConnectionPool() {
		initConnectionPool();
	}
	
	private void initConnectionPool(){
		if(initialized) {
			return;
		}
		cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(DRIVER);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		cpds.setJdbcUrl(URL);
		cpds.setUser(USER);
		cpds.setPassword(PASSWORD);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(180);
		
		initialized = true; 
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = this.cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
