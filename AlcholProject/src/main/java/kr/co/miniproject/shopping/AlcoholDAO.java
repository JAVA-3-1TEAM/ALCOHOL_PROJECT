package kr.co.miniproject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class AlcoholDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<AlcoholVO> selectAl(String al_type) {
		List<AlcoholVO> list = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.show_alList();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, al_type);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<AlcoholVO>();

			while(rs.next()) {
				AlcoholVO alVO = new AlcoholVO(rs.getInt("AL_ID"),
						rs.getString("AL_NAME"),
						rs.getString("AL_TYPE"),
						rs.getInt("AL_PRICE"));
				
				list.add(alVO);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			JDBC_Close.closeConnStmt(conn, pstmt);

		}
		 
		
		return list;
	}
	
	
	
	

}
