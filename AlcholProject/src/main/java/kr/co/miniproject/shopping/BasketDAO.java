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
import kr.co.miniproject.menu.ShoppingScreen;
import kr.co.miniproject.orders.OrdersDAO;
import kr.co.miniproject.orders.OrdersVO;

public class BasketDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ShoppingScreen shopScreen = new ShoppingScreen();
	ResultSet rs = null;
	Scanner scanner = new Scanner(System.in);
	OrdersDAO oDAO = new OrdersDAO();

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
				if (totalPrice == 0) {
					return null;
				}
			}
			String name = basketList.get(0).getName();
			System.out.println(name + "님(" + idEmail + ")의 장바구니 목록입니다.\n");

			for (BasketVO b : basketList) {
				shopScreen.BasketAll(b);
				basketNum.add(b.getAlId());
			}
			shopScreen.endLine();
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
			int alId = basketaddVO.getAlId();
			int cnt = basketaddVO.getCntNumber();
			String sql2 = JDBC_SQL.selectBaksetAlId_AlId();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, alId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String sql3 = JDBC_SQL.updateBasketCntNum_addCnt_AlId();
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, cnt);
				pstmt.setInt(2, alId);
				result += pstmt.executeUpdate();
			} else {
				String sql = JDBC_SQL.basket_add();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Email);
				pstmt.setInt(2, basketaddVO.getAlId());
				pstmt.setInt(3, basketaddVO.getCntNumber());
				result += pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
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
			if (check == 0) {
				System.out.println("잘못된 정보입니다. 이전페이지로 이동합니다.");
				return;
			}

			// 생성된 주문목록에서 orderNum가져오기.
			sql = JDBC_SQL.selectOrderNum_idEmail();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNum = rs.getInt("ORDER_NUM");
			}

			// 주문목록에서 가져온 orderNum을 장바구니 컬럼에 추가.
			sql = JDBC_SQL.updateBasket_orderNumIdEmail();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.setString(2, Email);
			check = pstmt.executeUpdate();

			List<OrdersVO> orderList = oDAO.orderList(Email, orderNum);
			oDAO.printOrderList(Email, orderList, orderNum);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
	}
}
