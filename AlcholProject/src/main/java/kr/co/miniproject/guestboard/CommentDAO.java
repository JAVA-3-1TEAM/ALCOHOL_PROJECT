package kr.co.miniproject.guestboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;
import common.util.LoginMember;

public class CommentDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	String idEmail = LoginMember.loginId;
	public int insertOne(CommentVO comVO) {
		int result = 0;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.writeComment();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comVO.getContent());
			pstmt.setString(2, comVO.getIdEmail());
			pstmt.setInt(3, comVO.getReqNum());
			result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("답변이 등록되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}

	// 리퀘번호당 해당하는 코멘트와 함께보도록 조인 한 쿼리문 불러오기 (SELECT문)
	// req_num, com_num, content, id_email, date

	public List<CommentRequestVO> selectComReqAll(int reqNum) {
		List<CommentRequestVO> crList = null;
		ResultSet rs;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.showReqCom();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			crList = new ArrayList<CommentRequestVO>();
			while (rs.next()) {
				if (rs.getInt("문의번호") == reqNum) {
					CommentRequestVO crvo = new CommentRequestVO(rs.getInt("문의번호"), rs.getInt("답글번호"),
							rs.getString("문의제목"), rs.getString("문의내용"), rs.getString("답글내용"), rs.getString("작성자"),
							rs.getString("문의접수일"));
					crList.add(crvo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return crList;
	}

	public int chgCom(CommentVO comVO) {
		int result = 0;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.changeCom();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comVO.getContent());
			pstmt.setInt(2, comVO.getComNum());
			result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("답글 수정이 완료되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
}
