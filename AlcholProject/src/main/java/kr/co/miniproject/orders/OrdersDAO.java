package kr.co.miniproject.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class OrdersDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<OrdersVO> orderList(String idEmail) {
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		conn = JDBC_Connect.getConnection();
		try {
			String sql = JDBC_SQL.userOrderList_Email();
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

				OrdersVO orderVO = new OrdersVO(orderNum, idEmail, name, phone, address, alName, cntNumber, price,
						orgDate);
				orderList.add(orderVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return orderList;
	}

	public void printOrderList(String idEmail, List<OrdersVO> orderList) {
		conn = JDBC_Connect.getConnection();
		String sql = JDBC_SQL.orderTotalPrice_Email();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();
			int totalPrice = 0;
			if (rs.next()) {
				totalPrice = rs.getInt("TOTAL_PRICE");
			}
			String id = orderList.get(0).getIdEmail();
			String name = orderList.get(0).getName();
			System.out.println(name + "님(" + id + ")의 장바구니 목록입니다.\n");
			for (OrdersVO o : orderList) {
				System.out.println("=============================");
				System.out.println(o);
			}
			System.out.println("=============================");
			System.out.println("총 금액 : " + totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

	}

	public void orderDelete(String idEmail, int orderNum) {
		conn = JDBC_Connect.getConnection();
		String sql = JDBC_SQL.deleteOrderList_Email_OrderNum();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			pstmt.setInt(2, orderNum);
			rs = pstmt.executeQuery();
			System.out.println("장바구니에서 취소되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
	}
}
