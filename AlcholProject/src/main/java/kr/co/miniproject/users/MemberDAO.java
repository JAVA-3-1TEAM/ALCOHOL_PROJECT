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

		try {
			String sql = JDBC_SQL.signUp();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId_email());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getPhone());
			result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				System.out.println("회원가입이 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("회원가입이 실패했습니다.");
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}

	public MemberVO selectOne(String email, String pwd) {
		ResultSet rs = null;
		conn = JDBC_Connect.getConnection();

		String sql = JDBC_SQL.login_Email();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			// 전달받은 id와 일치하는 데이터가 있을 경우.
			if (rs.next()) {
				// pwd가 맞는지 확인한 후 객체 생성해서 반환.
				if (rs.getString("pwd").equals(pwd)) {
					String name = rs.getString("name");
					String birth = rs.getString("birth");
					String phone = rs.getString("phone");
					MemberVO mvo = new MemberVO(email, pwd, name, birth, phone);
					return mvo;
				} 
//				else {
//					System.out.println("==================================");
//					System.out.println("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
//				}
			} 
//			else {
//				System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
