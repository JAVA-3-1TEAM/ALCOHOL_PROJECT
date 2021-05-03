package kr.co.miniproject.mypage;

import java.sql.Connection;
import java.sql.Date;
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

	public List<ReqlistVO> myRequestList(String idEmail){
		List<ReqlistVO> reqList = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.showMyReqList();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();
			
			reqList = new ArrayList<ReqlistVO>();
			while (rs.next()) {			
				/*
				 REQ_NUM, TITLE, CONTENT, TO_CHAR(W_DATE,'YY-MM-DD') AS W_DATE, COMMENTS, ID_EMAIL
				 */
				int reqNum = rs.getInt("REQ_NUM");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String wDate = rs.getString("W_DATE");
				String name = rs.getString("NAME");
				ReqlistVO rvo = new ReqlistVO(reqNum, title, content, name, wDate);
				reqList.add(rvo);
			}
			
			for(ReqlistVO rvo : reqList) {
				System.out.println("=========================================================");
				System.out.println(rvo);
			}
			System.out.println("=========================================================");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		
		return reqList;
	}	
	
//	public List<ReqlistVO> myRequestDelete(String idEmail){
//		conn= JDBC_Connect.getConnection();
//		try {
//			String sql = "delete reqboard where ID_EMAIL='test1' and REQ_NUM = 1";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//	}
}
