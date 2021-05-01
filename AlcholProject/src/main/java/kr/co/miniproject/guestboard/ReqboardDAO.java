package kr.co.miniproject.guestboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class ReqboardDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	Scanner scanner = new Scanner(System.in);
	
	public int insertOne(ReqboardVO reqVO) {
		int result = 0;
		try {
		
			//게시글 입력받도록 쓰는 insert문
			conn = JDBC_Connect.getConnection();
			
			String sql = JDBC_SQL.write_req();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, reqVO.getTitle());
			pstmt.setString(2, reqVO.getContent());
			pstmt.setString(3, reqVO.getId_email());

			result = pstmt.executeUpdate();
			
			if(result != 0) {
				System.out.println("게시글이 등록되었습니다.");
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1; //db오류시 다시 돌아가기
	}
	
}
