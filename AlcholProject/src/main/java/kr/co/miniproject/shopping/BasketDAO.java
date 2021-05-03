package kr.co.miniproject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class BasketDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//Basket 테이블의 데이터 보여주기
	public List<BasketVO> selectBasket(){
		List<BasketVO> bList = null;
		
		try {
			conn = JDBC_Connect.getConnection();
			
			String sql = JDBC_SQL.show_basket();			
			pstmt = conn.prepareStatement(sql);
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
		
	//장바구니 목록에서 제거하는 메서드
	public int deletebasket(BasketVO basketVO){
		System.out.println(basketVO);
		int delresult = 0;
		try {
			conn = JDBC_Connect.getConnection();
			
			String sql = JDBC_SQL.deletebasket();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,basketVO.getAl_id());
			delresult = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}		
		return delresult;		
	}
	
	//장바구니에 추가하기DAO
		public int insertBasket(BasketVO basketaddVO) {
			int result = 0;
			try {
				conn = JDBC_Connect.getConnection();
				String sql = JDBC_SQL.basket_add();
				pstmt = conn.prepareStatement(sql);
				
				//한번 로그인하면 로그인상태 유지로 계속해서 email 받을거니까
				//메서드로 받는 걸로 바꿔주기. 우선은 만드는 동안 t임시로 넣어둠.
				String testId = "abc@gmail.com";
				pstmt.setString(1, testId);
				pstmt.setInt(2, basketaddVO.getAl_id());
				pstmt.setInt(3,  basketaddVO.getCnt_number());
				
				result = pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmt(conn, pstmt);
			}
			return result;
		}
	
	

}
