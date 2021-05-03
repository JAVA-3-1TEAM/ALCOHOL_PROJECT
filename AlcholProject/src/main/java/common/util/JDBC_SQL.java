package common.util;

public class JDBC_SQL {
	public static String signUp() {
		return "INSERT INTO MEMBERS (ID_EMAIL, PWD, NAME, BIRTH, PHONE) VALUES (?,?,?,?,?)";
	}
	
	public static String login_Email() {
		return "SELECT * FROM MEMBERS WHERE ID_EMAIL=?";
	}
	
	// 특정 사용자에 대한 주문내역 보여주기.
	public static String userOrderList_Email() {
		return "SELECT O.ORDER_NUM AS ORDER_NUM,M.ID_EMAIL, M.NAME, M.PHONE,  O.ADDRESS, "
				+ "A2.AL_NAME, B.CNT_NUMBER, B.CNT_NUMBER * A2.AL_PRICE AS \"가격\", "
				+ "TO_CHAR(O.ORGDATE,'MM-DD') AS ORGDATE\n"
				+ "FROM MEMBERS M INNER JOIN BASKET B\n"
				+ "    on M.ID_EMAIL = B.ID_EMAIL\n"
				+ "INNER JOIN ALCOHOL A2 on B.AL_ID = A2.AL_ID\n"
				+ "INNER JOIN ORDERS O\n"
				+ "    on B.BASKET_NUM = O.BASKET_NUM\n"
				+ "WHERE M.ID_EMAIL = ?";
	}
	
	// 주문테이블 토탈금액
	public static String orderTotalPrice_Email() {
		return "SELECT sum(TOTAL) AS TOTAL_PRICE\n"
				+ "FROM (SELECT  B.CNT_NUMBER * A2.AL_PRICE AS TOTAL\n"
				+ "FROM MEMBERS M INNER JOIN BASKET B\n"
				+ "    on M.ID_EMAIL = B.ID_EMAIL\n"
				+ "INNER JOIN ALCOHOL A2 on B.AL_ID = A2.AL_ID\n"
				+ "INNER JOIN ORDERS O\n"
				+ "    on B.BASKET_NUM = O.BASKET_NUM\n"
				+ "WHERE M.ID_EMAIL = ?)";
	}
	
	// 주문목록 삭제
	public static String deleteOrderList_Email_OrderNum() {
		return "DELETE ORDERS WHERE ID_EMAIL = ? AND ORDER_NUM = ?";
	}
	// 장바구니 리스트
	public static String userBasketList_Email() {
		return "select B.BASKET_NUM, B.ID_EMAIL, M.NAME, A2.AL_NAME,B.CNT_NUMBER, B.CNT_NUMBER * A2.AL_PRICE AS PRICE\n"
				+ "from BASKET B INNER JOIN ALCOHOL A2\n"
				+ "    on B.AL_ID = A2.AL_ID\n"
				+ "INNER JOIN MEMBERS M\n"
				+ "    on B.ID_EMAIL = M.ID_EMAIL\n"
				+ "WHERE M.ID_EMAIL = ?";
	}
	
	// 장바구니 테이블 토탈금액
	public static String basketTotalPrice_Email() {
		return "SELECT SUM(TOTAL) AS TOTAL_PRICE\n"
				+ "FROM (select B.CNT_NUMBER * A2.AL_PRICE AS TOTAL\n"
				+ "from BASKET B INNER JOIN ALCOHOL A2\n"
				+ "    on B.AL_ID = A2.AL_ID\n"
				+ "INNER JOIN MEMBERS M\n"
				+ "    on B.ID_EMAIL = M.ID_EMAIL\n"
				+ "    WHERE M.ID_EMAIL = ?)";
	}
	
	public static String show_alList() {
		return "SELECT AL_ID, AL_NAME, AL_TYPE, AL_PRICE FROM ALCOHOL WHERE AL_TYPE = ? ";
	}
	
	public static String show_basket() {
		return "SELECT ID_EMAIL, BASKET_NUM, AL_ID, CNT_NUMBER FROM BASKET";
	}
	
	public static String show_reqList() {
		return "SELECT R.W_DATE AS \"문의날짜\", M.NAME AS \"작성자\", \r\n"
				+ " R.TITLE AS \"문의제목\", R.CONTENT AS \"문의내역\", R.COMMENTS AS \"관리자 답변\""
				+ " FROM REQBOARD R, MEMBERS M WHERE R.ID_EMAIL = M.ID_EMAIL";		
	}
	
	// myPage 
	
	// 사용자가 작성한 글 목록 보여주기.
	public static String showReqList() {
		return "SELECT REQ_NUM, TITLE, CONTENT, TO_CHAR(W_DATE,'YY-MM-DD') AS W_DATE, M.NAME\n"
				+ "FROM REQBOARD R INNER JOIN MEMBERS M\n"
				+ "    on R.ID_EMAIL = M.ID_EMAIL\n"
				+ "WHERE M.ID_EMAIL = ?";
		
	}
	
	// 사용자가 작성한 댓글 보여주기.
	public static String showComment_Email() {
		return "SELECT COM_NUM, CONTENT,TO_CHAR(COM_DATE,'YY-MM-DD') AS COM_DATE, "
				+ "ID_EMAIL, REQ_NUM FROM COMMENTS WHERE ID_EMAIL=?";
	}
	public static String deleteReqList() {
		return "delete reqboard "
				+ "where ID_EMAIL=? and REQ_NUM = ?";
	}
	
	public static String write_req() {
		return "INSERT INTO REQBOARD(REQ_NUM, TITLE, CONTENT, ID_EMAIL) "
				+ "VALUES(SEQ_REQ.NEXTVAL, ?, ?, ?)";
	}
	

	public static String deletebasket() {
		return "DELETE FROM BASKET WHERE AL_ID = ?";
	}
	
	public static String basket_add() {
		return "INSERT INTO BASKET (BASKET_NUM, ID_EMAIL, AL_ID, CNT_NUMBER) "
				+ "VALUES (SEQ_BASK.nextval, ?, ?, ?)"; 
	}
}
