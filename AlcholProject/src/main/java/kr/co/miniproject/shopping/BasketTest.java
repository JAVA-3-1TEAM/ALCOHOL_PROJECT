//package kr.co.miniproject.shopping;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import common.util.JDBC_Close;
//import common.util.JDBC_Connect;
//import common.util.JDBC_SQL;
//
//public class BasketTest {
//	private static Connection conn = null;
//	private static PreparedStatement pstmt = null;
//	private static ResultSet rs = null;
//	static Scanner scanner = new Scanner(System.in);
//
//	public static void main(String[] args) {
//		insertOrderToBasket("abc@gmail.com");
//	}
//
//	// 장바구니에서 주문하기 테이블로 이동하기.
//	// INSERT INTO MINIP.ORDERS (ORDER_NUM, ID_EMAIL, ORGDATE, ADDRESS) VALUES (1,
//	// 2, 'abc@gmail.com', TO_DATE('2021-05-01 18:43:51', 'YYYY-MM-DD HH24:MI:SS'),
//	// '대한민국 서울');
//	public static void insertOrderToBasket(String Email) {
//		try {
//			int orderNum=0;
//			conn = JDBC_Connect.getConnection();
//			// 장바구니 테이블에서 id가 일치하고 ORDER_STATE가 0인 객체를 모두 가져온다.
//			// 주문테이블 생성하고 생성된 ORDER_NUM을 basket에도 저장한다.
//			System.out.print(">> 주소입력 : ");
//			String address = scanner.nextLine();
//			String sql = "INSERT INTO ORDERS (ORDER_NUM, ID_EMAIL, ORGDATE, ADDRESS) VALUES (SEQ_OR.nextval, ?,SYSDATE ,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Email);
//			pstmt.setString(2, address);
//			pstmt.executeUpdate();
//
//			sql = "select ORDER_NUM FROM ORDERS WHERE ID_EMAIL= ? ORDER BY ORGDATE DESC";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Email);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				orderNum = rs.getInt("ORDER_NUM");
//			}
//
//			sql = "update basket set order_num = ? where id_email=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, orderNum);
//			pstmt.setString(2, Email);
//			pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
//		}
//	}
//}
