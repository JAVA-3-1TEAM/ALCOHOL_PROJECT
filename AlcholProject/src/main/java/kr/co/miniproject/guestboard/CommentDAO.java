package kr.co.miniproject.guestboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class CommentDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public int insertOne(CommentVO comVO) {
		int result = 0; 
		try {
			conn = JDBC_Connect.getConnection();
			//INSERT INTO COMMENTS(COM_NUM, CONTENT, COM_DATE, ID_EMAIL, REQ_NUM)
			//VALUES(SEQ_COM.nextval, ?, SYSDATE, ?, ?);
			String sql = JDBC_SQL.writeComment();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comVO.getContent());
			//글쓴 사람의 아이디 가져오기 -> 현재 임의로 생성
			String comIdEmailTest = "admin@test.com";
			pstmt.setString(2, comIdEmailTest);
			//req넘버 선택하는 번호 그대로 가져올 수 있도록.
			pstmt.setInt(3, comVO.getReqNum());
			result = pstmt.executeUpdate();
			if(result != 0) {
				System.out.println("답변이 등록되었습니다.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
	
}



//INSERT INTO COMMENTS(COM_NUM, CONTENT, COM_DATE, ID_EMAIL, REQ_NUM)
//VALUES(SEQ_COM.nextval, ?, SYSDATE, ?, ?);