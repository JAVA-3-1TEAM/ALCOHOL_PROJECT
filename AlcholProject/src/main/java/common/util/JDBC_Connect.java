package common.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connect {
	public static Connection getConnection() {
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:XE";
//		String URL = "jdbc:oracle:thin:@192.168.0.22:1521:XE";
		String USER = "MINIP";
		String PASSWORD = "minip";
		Connection conn = null;

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
