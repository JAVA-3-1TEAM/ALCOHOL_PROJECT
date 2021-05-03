package kr.co.miniproject.mypage;

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
import kr.co.miniproject.guestboard.CommentVO;

public class ReqlistDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<ReqlistVO> myRequestList(String idEmail) {
		List<ReqlistVO> reqList = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.showMyReqList();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idEmail);
			rs = pstmt.executeQuery();

			reqList = new ArrayList<ReqlistVO>();
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
				ReqlistVO rvo = new ReqlistVO(reqNum, title, content, name, wDate);
				reqList.add(rvo);
			}

			for (ReqlistVO rvo : reqList) {
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

}
