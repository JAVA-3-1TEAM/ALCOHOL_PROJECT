package kr.co.miniproject.mypage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;
import kr.co.miniproject.guestboard.CommentDAO;
import kr.co.miniproject.guestboard.CommentVO;
import kr.co.miniproject.guestboard.ReqboardDAO;
import kr.co.miniproject.guestboard.ReqboardVO;
import kr.co.miniproject.users.MemberDAO;
import kr.co.miniproject.users.MemberVO;

public class MyReqlistDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	static ReqboardDAO reqDao = new ReqboardDAO();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static CommentDAO comDao = new CommentDAO();

	public List<MyReqlistVO> myRequestList(String idEmail) {
		List<MyReqlistVO> reqList = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.showMyReqList();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();

			reqList = new ArrayList<MyReqlistVO>();
			while (rs.next()) {
				/*
				 * REQ_NUM, TITLE, CONTENT, TO_CHAR(W_DATE,'YY-MM-DD') AS W_DATE, COMMENTS,
				 * ID_EMAIL
				 */
				int reqNum = rs.getInt("REQ_NUM");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String wDate = rs.getString("W_DATE");
				String name = rs.getString("NAME");
				MyReqlistVO rvo = new MyReqlistVO(reqNum, title, content, name, wDate);
				reqList.add(rvo);
			}

			for (MyReqlistVO rvo : reqList) {
				System.out.println("=========================================================");
				System.out.println(rvo);
			}
			System.out.println("=========================================================");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return reqList;
	}

	public void myRequestDelete(String idEmail, int reqNum) {
		conn = JDBC_Connect.getConnection();
		try {
			String sql = JDBC_SQL.deleteReqList();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idEmail);
			pstmt.setInt(2, reqNum);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
	}

	public List<CommentVO> myCommentsList(String myEmail) {
		List<CommentVO> cmtList = new ArrayList<CommentVO>();
		conn = JDBC_Connect.getConnection();
		try {
			String sql = JDBC_SQL.showComment_Email();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myEmail);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int comNum = rs.getInt("COM_NUM");
				String content = rs.getString("CONTENT");
				String comDate = rs.getString("COM_DATE");
				int reqNum = rs.getInt("REQ_NUM");
				CommentVO cmtVO = new CommentVO(comNum, reqNum, content, comDate, myEmail);
				cmtList.add(cmtVO);
			}
			for (CommentVO c : cmtList) {
				System.out.println("=======================================");
				System.out.println(c);
			}
			System.out.println("=======================================");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

		return cmtList;

	}

	public void myCommentDelete(String myEmail, int comNum) {
		conn = JDBC_Connect.getConnection();
		try {
			String sql = "DELETE COMMENTS WHERE ID_EMAIL =? AND COM_NUM =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myEmail);
			pstmt.setInt(2, comNum);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
	}

	public static void chgComWrite() throws IOException {
		System.out.println("변경할 답글 번호: ");
		int comNum = Integer.parseInt(br.readLine());
		System.out.println("새로운 답글 내용: ");
		String newComment = br.readLine();
		CommentVO comVO = new CommentVO(comNum, newComment);
		int cnt = comDao.chgCom(comVO);
		System.out.println(cnt + "건의 답글 수정완료");
	}

	// 문의글 변경하는 메서드
	public static void chgReqWrite() throws IOException {
		System.out.println("변경할 게시글 번호: ");
		int boardnum = Integer.parseInt(br.readLine());
		System.out.println("새로운 글 내용: ");
		String newRequest = br.readLine();
		ReqboardVO reqvo = new ReqboardVO(boardnum, newRequest);
		int cnt = reqDao.chgReq(reqvo);
		System.out.println(cnt + "건 업데이트 완료");
	}

	// 비밀번호 변경하는 메서드
	public static int chgPwd(String Email) throws IOException {
		MemberDAO dao = new MemberDAO();
		System.out.println("변경할 비밀번호: ");
		String chgpwd = br.readLine();
		MemberVO membervo = new MemberVO(Email, chgpwd);
		int cnt = dao.updatePwd(membervo);
		return cnt;
	}
}
