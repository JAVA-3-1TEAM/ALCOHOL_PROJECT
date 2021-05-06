package common.util;

public class JDBC_SQL {
	public static String signUp() {
		return "INSERT INTO MEMBERS (ID_EMAIL, PWD, NAME, BIRTH, PHONE) VALUES (?,?,?,?,?)";
	}

	public static String login_Email() {
		return "SELECT * FROM MEMBERS WHERE ID_EMAIL=?";
	}

	// 아이디 중복 확인
	public static String signUpIdOverlab_IdEmail() {
		return "SELECT * FROM MEMBERS WHERE ID_EMAIL =?";
	}

	// 특정 사용자에 대한 주문내역 보여주기. -> ORDER_NUM 컬럼으로 조(수정필요)
	public static String userOrderList_EmailOrderNum() {
		return "SELECT O.ORDER_NUM AS ORDER_NUM,M.ID_EMAIL, M.NAME, M.PHONE,  O.ADDRESS,\n"
				+ "				A2.AL_NAME, B.CNT_NUMBER, B.CNT_NUMBER * A2.AL_PRICE AS 가격,\n"
				+ "				TO_CHAR(O.ORGDATE,'YYYY-MM-DD') AS ORGDATE\n"
				+ "                FROM MEMBERS M INNER JOIN ORDERS O\n"
				+ "                    ON M.ID_EMAIL = O.ID_EMAIL\n" + "                INNER JOIN BASKET B\n"
				+ "                    ON O.ORDER_NUM = B.ORDER_NUM\n" + "                INNER JOIN ALCOHOL A2\n"
				+ "                    ON B.AL_ID = A2.AL_ID\n"
				+ "                WHERE M.ID_EMAIL = ? AND O.ORDER_NUM =?";
	}

	// 주문테이블 토탈금액
	public static String orderTotalPrice_Email() {
		return "SELECT sum(TOTAL) AS TOTAL_PRICE\n" + "				FROM (SELECT  B.CNT_NUMBER * A2.AL_PRICE AS TOTAL\n"
				+ "				FROM MEMBERS M INNER JOIN BASKET B\n"
				+ "				    on M.ID_EMAIL = B.ID_EMAIL\n"
				+ "				INNER JOIN ALCOHOL A2 on B.AL_ID = A2.AL_ID\n" + "				INNER JOIN ORDERS O\n"
				+ "				   on B.ORDER_NUM = O.ORDER_NUM\n"
				+ "				WHERE M.ID_EMAIL = ? AND O.ORDER_NUM =?)";
	}

	// 주문목록 삭제
	public static String deleteOrderList_Email_OrderNum() {
		return "DELETE ORDERS WHERE ID_EMAIL = ? AND ORDER_NUM = ?";
	}

	// 장바구니 리스트
	public static String userBasketList_Email() {
		return "select B.BASKET_NUM, B.ID_EMAIL, M.NAME, A2.AL_ID, A2.AL_NAME,B.CNT_NUMBER, B.CNT_NUMBER * A2.AL_PRICE AS PRICE\n"
				+ "from BASKET B INNER JOIN ALCOHOL A2\n" + "    on B.AL_ID = A2.AL_ID\n" + "INNER JOIN MEMBERS M\n"
				+ "    on B.ID_EMAIL = M.ID_EMAIL\n" + "WHERE M.ID_EMAIL = ? AND ORDER_NUM = 0";
	}
	// 장바구니에 담으려는 아이템이 이미 존재하는지 확인
	public static String selectBaksetAlId_AlId() {
		return "SELECT * FROM BASKET WHERE AL_id = ? AND ORDER_NUM = 0";
	}
	
	// 장바구니에 수량 변경 
	public static String updateBasketCntNum_addCnt_AlId() {
		return "UPDATE BASKET SET CNT_NUMBER = CNT_NUMBER+? WHERE AL_ID = ? AND ORDER_NUM = 0";
	}

	// 장바구니 테이블 토탈금액
	public static String basketTotalPrice_Email() {
		return "SELECT SUM(TOTAL) AS TOTAL_PRICE\n" + "FROM (select B.CNT_NUMBER * A2.AL_PRICE AS TOTAL\n"
				+ "from BASKET B INNER JOIN ALCOHOL A2\n" + "    on B.AL_ID = A2.AL_ID\n" + "INNER JOIN MEMBERS M\n"
				+ "    on B.ID_EMAIL = M.ID_EMAIL\n" + "    WHERE M.ID_EMAIL = ? AND ORDER_NUM = 0)";
	}

	// 장바구니에서 주문테이블로 옮기기
	public static String selectBasketEmailOrderNum() {
		return "SELECT BASKET_NUM, ID_EMAIL FROM BASKET WHERE ID_EMAIL = ? AND ORDER_NUM = 0";

	}

	// 주소 입력받아서 주문목록 생성
	public static String insertOrders_EmailAddress() {
		return "INSERT INTO ORDERS (ORDER_NUM, ID_EMAIL, ORGDATE, ADDRESS) VALUES (SEQ_OR.nextval, ?,SYSDATE ,?)";
	}

	// 주문목록에서 ordernum가져오기
	public static String selectOrderNum_idEmail() {
		return "select ORDER_NUM FROM ORDERS WHERE ID_EMAIL= ? ORDER BY ORGDATE DESC";
	}

	// 장바구니 ordernum = 주문목록 ordernum
	public static String updateBasket_orderNumIdEmail() {
		return "update basket set order_num = ? where id_email=? AND ORDER_NUM = 0";
	}

	// 주문번호 리스트에 넣기
	public static String selectOrderNum_IdEmail() {
		return "select distinct ORDER_NUM from ORDERS WHERE ORDER_NUM NOT IN 0 AND ID_EMAIL =?";
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
	public static String showMyReqList() {
		return "SELECT REQ_NUM, TITLE, CONTENT, TO_CHAR(W_DATE,'YY-MM-DD') AS W_DATE, M.NAME\n"
				+ "FROM REQBOARD R INNER JOIN MEMBERS M\n" + "    on R.ID_EMAIL = M.ID_EMAIL\n"
				+ "WHERE M.ID_EMAIL = ?";

	}

	// 사용자가 작성한 댓글 보여주기.
	public static String showComment_Email() {
		return "SELECT COM_NUM, CONTENT,TO_CHAR(COM_DATE,'YY-MM-DD') AS COM_DATE, "
				+ "ID_EMAIL, REQ_NUM FROM COMMENTS WHERE ID_EMAIL=?";
	}

	public static String deleteReqList() {
		return "delete reqboard " + "where ID_EMAIL=? and REQ_NUM = ?";
	}

	public static String write_req() {
		return "INSERT INTO REQBOARD(REQ_NUM, TITLE, CONTENT, ID_EMAIL) " + "VALUES(SEQ_REQ.NEXTVAL, ?, ?, ?)";
	}

	// 장바구니 품목제거
	public static String deleteBasket() {
		return "DELETE FROM BASKET WHERE AL_ID = ?";
	}

	public static String basket_add() {
		return "INSERT INTO BASKET (BASKET_NUM, ID_EMAIL, AL_ID, CNT_NUMBER) " + "VALUES (SEQ_BASK.nextval, ?, ?, ?)";
	}

	public static String writeComment() {
		return "INSERT INTO COMMENTS(COM_NUM, CONTENT, COM_DATE, ID_EMAIL, REQ_NUM) "
				+ "VALUES(SEQ_COM.nextval, ?, SYSDATE, ?, ?)";
	}

	public static String showAllReq() {
		return "SELECT * FROM REQBOARD ORDER BY W_DATE DESC";
	}

	public static String showReqCom() {
		return "SELECT C.REQ_NUM AS \"문의번호\", R.TITLE as \"문의제목\", R.CONTENT AS \"문의내용\",C.COM_NUM AS \"답글번호\", C.CONTENT AS \"답글내용\", C.ID_EMAIL AS \"작성자\", R.W_DATE AS \"문의접수일\" "
				+ "FROM COMMENTS C, REQBOARD R WHERE R.REQ_NUM = C.REQ_NUM " + "ORDER BY C.REQ_NUM ";
	}

	public static String changePwd() {
		return "UPDATE MEMBERS SET PWD = ? " + "WHERE ID_EMAIL = ? ";
	}

	public static String changeReq() {
		return "UPDATE REQBOARD SET CONTENT = ? WHERE REQ_NUM = ?";
	}

	public static String changeCom() {
		return "UPDATE COMMENTS SET CONTENT = ? WHERE COM_NUM = ?";
	}

}
