package kr.co.miniproject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;
import kr.co.miniproject.orders.OrdersVO;
import oracle.net.aso.b;

public class BasketDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public List<BasketVO> basketList(String idEmail) {
		List<BasketVO> basketList = new ArrayList<BasketVO>();
		conn = JDBC_Connect.getConnection();
		// 토탈금액까지 넘겨줘서 보여줘야하는데 어떻게? 따로 토탈 메소드 빼서 계산하기? -->아래 print 메서드로 추가.
		// 장바구니에 중복된 데이터 추가하면 어떻게 처리? -> 탐색해서 같은 고유값을 가진 술이 있으면 더하거나 빼주기.
		try {
			String sql = JDBC_SQL.userOrder_Email();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String orderNum = rs.getString("ORDER_NUM");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String address = rs.getString("Address");
				String alName = rs.getString("AL_NAME");
				String cntNumber = rs.getString("CNT_NUMBER");
				int price = rs.getInt("가격");
				String orgDate = rs.getString("ORGDATE");

				BasketVO basketVO = new BasketVO(orderNum, idEmail, name, phone, address, alName, cntNumber, price,
						orgDate);
				basketList.add(basketVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return basketList;
	}

	public void printOrderList(String idEmail, List<BasketVO> basketList) {
		conn = JDBC_Connect.getConnection();
		String sql = JDBC_SQL.totalPrice_Email();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();
			int totalPrice = 0;
			if (rs.next()) {
				totalPrice = rs.getInt("TOTAL_PRICE");
			}
			String id = basketList.get(0).getIdEmail();
			String name = basketList.get(0).getName();
			System.out.println(name + "님(" + id +")의 장바구니 목록입니다.\n");
			
			for (BasketVO b : basketList) {
				System.out.println("=============================");
				System.out.println(b);
			}
			System.out.println("=============================");
			System.out.println("총 금액 : " + totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

	}
	
	

}
