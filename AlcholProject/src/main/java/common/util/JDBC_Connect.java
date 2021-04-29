package common.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connect {
	public static Connection getConnection() {
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		String USER = "MINIP";
		String PASSWORD = "minip";
		Connection conn = null;

		try {
			Class.forName(DRIVER);
			System.out.println("Oracle 드라이버 로딩 성공.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			System.out.println("jdbc driver 로딩 실패");
			e.printStackTrace();
		}
		System.out.println(conn);
		return conn;
	}
}
