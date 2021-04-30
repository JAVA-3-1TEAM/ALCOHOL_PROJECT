package kr.co.miniproject.users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public int insertOne(MemberVO member) {
		int result = 0;
		// exception 처리 -> JDBC_Connect에서 오류가 발생해서 null값이 넘어올 수 있다.
		conn = JDBC_Connect.getConnection();
		System.out.println(conn);
		
		try {
			// DB 연결 - Connection 객체 생성(DB와 연결된)
			// SQL문 실행
			String sql = JDBC_SQL.signUp();
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			// ? 바인드변수에 값 설정
			pstmt.setString(1, member.getId_email());
			pstmt.setString(2,member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getPhone());
			// SQL문 실행(INSERT, UPDATE, DELETE - executeUpdate())
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
}


