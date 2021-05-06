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
import kr.co.miniproject.shopping.AlcoholVO;
import kr.co.miniproject.users.MemberVO;

public class ReqboardDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	Scanner scanner = new Scanner(System.in);
	CommentDAO comDao = new CommentDAO();

	public int insertReq(ReqboardVO reqVO) {
		int result = 0;
		try {

			// 게시글 입력받도록 쓰는 insert문
			conn = JDBC_Connect.getConnection();

			String sql = JDBC_SQL.write_req();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, reqVO.getTitle());
			pstmt.setString(2, reqVO.getContent());
			pstmt.setString(3, reqVO.getId_email());

			result = pstmt.executeUpdate();

			if (result != 0) {
				System.out.println("게시글이 등록되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1; // db오류시 다시 돌아가기
	}

	// 글 전체 리스트 보여주는거
	// 선택지 만들고
	// req넘버 선택했을 때 댓글
	public List<ReqboardVO> selectAllReq() {
		List<ReqboardVO> reqVOlist = null;
		ResultSet rs = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.showAllReq();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			reqVOlist = new ArrayList<ReqboardVO>();
			while (rs.next()) {
				ReqboardVO reqvo = new ReqboardVO(rs.getInt("REQ_NUM"), rs.getString("TITLE"), rs.getString("CONTENT"),
						rs.getString("W_DATE"), rs.getString("ID_EMAIL"));
				reqVOlist.add(reqvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return reqVOlist;
	}

	public int chgReq(ReqboardVO reqVO) {
		int result = 0;
		try {
			conn = JDBC_Connect.getConnection();
			String sql = JDBC_SQL.changeReq();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reqVO.getContent());
			pstmt.setInt(2, reqVO.getReq_num());

			result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("게시글 내용 수정이 완료되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}

	public void showReqAndCom(int reqNum) {
		comDao = new CommentDAO();
		List<CommentRequestVO> crList = comDao.selectComReqAll(reqNum);
		if (crList.size() != 0) {
			for (CommentRequestVO crvo : crList) {
				System.out.println("===================================");
				System.out.println(crvo);
			}
			System.out.println("===================================");
		} else {
			System.out.println("아직 답변이 없습니다.");
		}
	}

}
