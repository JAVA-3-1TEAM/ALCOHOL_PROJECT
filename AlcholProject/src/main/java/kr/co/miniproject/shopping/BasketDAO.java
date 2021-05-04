package kr.co.miniproject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class BasketDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	Scanner scanner = new Scanner(System.in);

	public List<BasketVO> basketList(String idEmail) {
		List<BasketVO> basketList = new ArrayList<BasketVO>();
		conn = JDBC_Connect.getConnection();
		// 장바구니에 중복된 데이터 추가하면 어떻게 처리? -> 탐색해서 같은 고유값을 가진 술이 있으면 더하거나 빼주기.
		try {
			String sql = JDBC_SQL.userBasketList_Email();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int basketNum = rs.getInt("BASKET_NUM");
				String idEamil = rs.getString("ID_EMAIL");
				String name = rs.getString("NAME");
				String alName = rs.getString("AL_NAME");
				int alId = rs.getInt("AL_ID");
				int cntNumber = rs.getInt("CNT_NUMBER");
				int price = rs.getInt("PRICE");

				BasketVO basketVO = new BasketVO(basketNum, idEmail, name, alId, alName, cntNumber, price);
				basketList.add(basketVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return basketList;
	}

	public List<Integer> printOrderList(String idEmail, List<BasketVO> basketList) {
		conn = JDBC_Connect.getConnection();
		String sql = JDBC_SQL.basketTotalPrice_Email();
		List<Integer> basketNum = new ArrayList<Integer>();
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
			System.out.println(name + "님(" + id + ")의 장바구니 목록입니다.\n");

			for (BasketVO b : basketList) {
				System.out.println("=============================");
				System.out.println(b);
				basketNum.add(b.getAlId());
			}
			System.out.println("=============================");
			System.out.println("총 금액 : " + totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return basketNum;
	}

	// 장바구니 목록에서 제거하는 메서드
	public int deletebasket(int alId) {
		int delresult = 0;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.deleteBasket();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, alId);
			delresult = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return delresult;
	}

	// 장바구니에 추가하기DAO
	public int insertBasket(String Email, BasketVO basketaddVO) {
		int result = 0;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.basket_add();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, Email);
			pstmt.setInt(2, basketaddVO.getAlId());
			pstmt.setInt(3, basketaddVO.getCntNumber());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}

	// 장바구니에서 주문하기 테이블로 이동하기.
	public void insertOrderToBasket(String Email) {
		try {
			int orderNum = 0;
			int check = 0;
			conn = JDBC_Connect.getConnection();
			// 장바구니 테이블에서 id가 일치하고 ORDER_STATE가 0인 객체를 모두 가져온다.
			// 주문테이블 생성하고 생성된 ORDER_NUM을 basket에도 저장한다.
			System.out.print(">> 주소입력 : ");
			String address = scanner.nextLine();
			String sql = JDBC_SQL.insertOrders_EmailAddress();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			pstmt.setString(2, address);
			check = pstmt.executeUpdate();
			if(check ==0) {
				System.out.println("잘못된 정보입니다. 이전페이지로 이동합니다.");
				return;
			}

			sql = JDBC_SQL.selectOrderNum_idEmail();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNum = rs.getInt("ORDER_NUM");
			}

			sql = JDBC_SQL.updateBasket_orderNumIdEmail();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.setString(2, Email);
			check = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
	}
}
