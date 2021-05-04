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
	
	public int insertReq(ReqboardVO reqVO) {
		int result = 0;
		try {
		
			//게시글 입력받도록 쓰는 insert문
			conn = JDBC_Connect.getConnection();
			
			String sql = JDBC_SQL.write_req();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, reqVO.getTitle());
			pstmt.setString(2, reqVO.getContent());
			pstmt.setString(3, reqVO.getId_email());

			result = pstmt.executeUpdate();
			
			if(result != 0) {
				System.out.println("게시글이 등록되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1; //db오류시 다시 돌아가기
	}
	
	
	//글 전체 리스트 보여주는거
	//선택지 만들고
	//req넘버 선택했을 때 댓글 
	public List<ReqboardVO> selectAllReq(){
		List<ReqboardVO> reqVOlist = null;
		ResultSet rs = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.showAllReq();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			reqVOlist = new ArrayList<ReqboardVO>();			
			while(rs.next()) {
				ReqboardVO reqvo = new ReqboardVO(rs.getInt("REQ_NUM"),
						rs.getString("TITLE"),
						rs.getString("CONTENT"),
						rs.getString("W_DATE"),
						rs.getString("ID_EMAIL"));				
				reqVOlist.add(reqvo);
			}				
			
		} catch (Exception e) {
			e.printStackTrace();			
		}finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}		 
		
		return reqVOlist;
	}
	
	
	public int updateReq(ReqboardVO reqVO) {
		int result = 0;
		try {
			conn = JDBC_Connect.getConnection();			
			String sql = JDBC_SQL.changeReq();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reqVO.getContent());
			pstmt.setInt(2, reqVO.getReq_num());
			System.out.println(sql);
			result = pstmt.executeUpdate();
			if(result != 0) {
				System.out.println("게시글 내용 변경이 완료되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
		
	}
	
}
