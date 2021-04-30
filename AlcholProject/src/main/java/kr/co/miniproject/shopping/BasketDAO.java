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
	
	//Basket 테이블의 데이터 보여주기
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
	
	
	
	//Basket테이블의 이름만 따로 문장처리하도록 만든 메서드
	public String userBasket(String id_email) {
		String b_name = "";
		try {
			
			conn = JDBC_Connect.getConnection();
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT NAME FROM MEMBERS WHERE ID_EMAIL = ?");
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b_name = rs.getString("NAME");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return b_name;
	}
	
	
	//상품 상세정보를 가져올 쿼리문이 있는 메서드
	
		public List<AlcoholOrdersVO> basketalcohol() {
			List<AlcoholOrdersVO> list = null;
			
			try {
				conn = JDBC_Connect.getConnection();
				StringBuilder sb = new StringBuilder();
				
				sb.append("SELECT B.BASKET_NUM, B.AL_ID, A.AL_NAME, A.AL_TYPE, AL_PRICE, B.CNT_NUMBER ");
				sb.append("FROM ALCOHOL A, BASKET B WHERE A.AL_ID = B.AL_ID");

				pstmt = conn.prepareStatement(sb.toString());
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<AlcoholOrdersVO>();
				while(rs.next()) {
					AlcoholOrdersVO aovo = new AlcoholOrdersVO(
							rs.getInt("BASKET_NUM"),							
							rs.getInt("AL_ID"),
							rs.getString("AL_NAME"),
							rs.getString("AL_TYPE"),
							rs.getInt("AL_PRICE"),
							rs.getInt("CNT_NUMBER")
							);
					list.add(aovo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBC_Close.closeConnStmt(conn, pstmt);
			}
			return list;
		}
		
	
	

}
