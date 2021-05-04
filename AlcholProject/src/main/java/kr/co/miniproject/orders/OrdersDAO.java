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
import kr.co.miniproject.shopping.BasketVO;

public class OrdersDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<OrdersVO> orderList(String idEmail, int orderNum) {
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		conn = JDBC_Connect.getConnection();
		try {
			String sql = JDBC_SQL.userOrderList_EmailOrderNum();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			pstmt.setInt(2, orderNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public void printOrderList(String idEmail, List<OrdersVO> orderList, int orderNum) {
		conn = JDBC_Connect.getConnection();
		String sql = JDBC_SQL.orderTotalPrice_Email();
		try {
			if (orderList.size() != 0) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, idEmail);
				pstmt.setInt(2, orderNum);
				rs = pstmt.executeQuery();
				int totalPrice = 0;
				if (rs.next()) {
					totalPrice = rs.getInt("TOTAL_PRICE");
				}
				String id = idEmail;
				String name = orderList.get(0).getName();
				System.out.println(name + "님(" + id + ")의 주문하신 목록입니다.\n");
				for (OrdersVO o : orderList) {
					System.out.println("=============================");
					System.out.println(o);
				}
				System.out.println("=============================");
				System.out.println("총 금액 : " + totalPrice);
			} else {
				System.out.println("주문내역이 없습니다.");
			}
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
			System.out.println("주문목록이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
	}

	public List<Integer> addOrderNumList(String Email) {
		conn = JDBC_Connect.getConnection();
		String sql = JDBC_SQL.selectOrderNum_IdEmail();
		List<Integer> orderNumList = new ArrayList<Integer>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			rs = pstmt.executeQuery();
			System.out.println("장바구니 olderNum 전송");
			while(rs.next()) {
				int orderNum = rs.getInt("ORDER_NUM");
				orderNumList.add(orderNum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return orderNumList;
	}

}
