package kr.co.miniproject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;

public class BasketDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<BasketVO> selectBasket(){
		List<BasketVO> bList = null;
		
		try {
			conn = JDBC_Connect.getConnection();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ID_EMAIL, BASKET_NUM, AL_ID, CNT_NUMBER ");
			sb.append("FROM BASKET");
			
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			bList = new ArrayList<BasketVO>();
			while(rs.next()) {
				BasketVO bvo = new BasketVO(
						rs.getInt("BASKET_NUM"), 
						rs.getString("ID_EMAIL"), 
						rs.getInt("AL_ID"), 
						rs.getInt("CNT_NUMBER"));
				
				bList.add(bvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
			
		return bList;
	}
	
}
