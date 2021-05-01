package common.util;

public class JDBC_SQL {
	public static String signUp() {
		return "INSERT INTO MEMBERS (ID_EMAIL, PWD, NAME, BIRTH, PHONE) VALUES (?,?,?,?,?)";
	}
	
	public static String login_Email() {
		return "SELECT * FROM MEMBERS WHERE ID_EMAIL=?";
	}
	
	public static String userOrder_Email() {
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
	
	public static String totalPrice_Email() {
		return "SELECT sum(TOTAL) AS TOTAL_PRICE\n"
				+ "FROM (SELECT  B.CNT_NUMBER * A2.AL_PRICE AS TOTAL\n"
				+ "FROM MEMBERS M INNER JOIN BASKET B\n"
				+ "    on M.ID_EMAIL = B.ID_EMAIL\n"
				+ "INNER JOIN ALCOHOL A2 on B.AL_ID = A2.AL_ID\n"
				+ "INNER JOIN ORDERS O\n"
				+ "    on B.BASKET_NUM = O.BASKET_NUM\n"
				+ "WHERE M.ID_EMAIL = ?)";
	}
	
	public static String show_alList() {
		return "SELECT AL_ID, AL_NAME, AL_TYPE, AL_PRICE FROM ALCOHOL ";
	}
	
	public static String show_basket() {
		return "SELECT ID_EMAIL, BASKET_NUM, AL_ID, CNT_NUMBER FROM BASKET";
	}
	
	public static String show_reqList() {
		return "SELECT R.W_DATE AS \"문의날짜\", M.NAME AS \"작성자\", \r\n"
				+ " R.TITLE AS \"문의제목\", R.CONTENT AS \"문의내역\", R.COMMENTS AS \"관리자 답변\""
				+ " FROM REQBOARD R, MEMBERS M WHERE R.ID_EMAIL = M.ID_EMAIL";		
	}
	
	public static String write_req() {
		return "INSERT INTO REQBOARD(REQ_NUM, TITLE, CONTENT, ID_EMAIL) "
				+ "VALUES(SEQ_REQ.NEXTVAL, ?, ?, ?)";
	}
}
