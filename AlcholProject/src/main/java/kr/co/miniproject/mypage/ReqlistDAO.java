package kr.co.miniproject.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class ReqlistDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<ReqlistVO> requestList(){
		List<ReqlistVO> reqList = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.show_reqList();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			reqList = new ArrayList<ReqlistVO>();
			while (rs.next()) {
				ReqlistVO reqVO = new ReqlistVO(rs.getDate("문의날짜"), // rs.getDate("w_date"),
						rs.getString("작성자"), // rs.getString("name"),
						rs.getString("문의제목"), // rs.getString("title"),
						rs.getString("문의내역"), // rs.getString("content"),
						rs.getString("관리자 답변"));// rs.getString("comments")				
				reqList.add(reqVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		
		return reqList;
	}
	
	
}
