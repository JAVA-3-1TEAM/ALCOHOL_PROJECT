package kr.co.miniproject.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	Date d = new Date();
	SimpleDateFormat s = new SimpleDateFormat("YYYY");

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
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

		return null;
	}

	// 비밀번호 바꾸는 쿼리문이 들어있는 DAO 메소드
	public int updatePwd(MemberVO membervo) {
		int result = 0;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.changePwd();
			pstmt = conn.prepareStatement(sql);
			// Email은 메서드로 가져와서 값을 받아야함. 현재상태에서는 임의 이메일 주소 설정.
			pstmt.setString(1, membervo.getPwd());
			pstmt.setString(2, membervo.getId_email());
			result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("비밀번호 변경이 완료되었습니다.");
				System.out.println("변경된 비밀번호는 : " + membervo.getPwd() + "입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}

	// 미성년자 확인
	public boolean checkBirth(String birth) {
		String chBirth = birth.substring(0, 4);
		int cBirth = Integer.parseInt(chBirth);
		int y = Integer.parseInt(s.format(d));
		int age = y - cBirth + 1;
		if (age < 20) {
			return false;
		}
		return true;
	}

	// 아이디 중복 확인
	public boolean overlabId(String idEmail) {
		conn = JDBC_Connect.getConnection();
		String sql = "SELECT * FROM MEMBERS WHERE ID_EMAIL =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return true;
	}
}
