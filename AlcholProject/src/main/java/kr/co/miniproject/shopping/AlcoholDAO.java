package kr.co.miniproject.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;
import common.util.JDBC_Connect;
import common.util.JDBC_SQL;

public class AlcoholDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<AlcoholVO> alcoholList(String al_type) {
		List<AlcoholVO> alList = null;
		conn = JDBC_Connect.getConnection();

		try {
			String sql = JDBC_SQL.show_alList();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, al_type);
			rs = pstmt.executeQuery();

			alList = new ArrayList<AlcoholVO>();

			while (rs.next()) {
				AlcoholVO alVO = new AlcoholVO(rs.getInt("AL_ID"), rs.getString("AL_NAME"), rs.getString("AL_TYPE"),
						rs.getInt("AL_PRICE"));
				alList.add(alVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return alList;
	}

	public void basketAdd(String Email, List<AlcoholVO> alList) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BasketDAO basketDAO = new BasketDAO();
		int cnt = 0;
		while (true) {
			for (AlcoholVO al : alList) {
				System.out.println(al);
			}
			System.out.println("장바구니에 추가를 원하는 상품번호를 입력해주세요. 뒤로 가시려면 0번을 눌러주세요.");
			System.out.print(">> ");
			int userAlChoice = (Integer.parseInt(br.readLine()));
			if(userAlChoice == 0)
				break;
			System.out.println("상품의 수량을 입력해주세요");
			System.out.print(">> ");
			int userCntChoice = (Integer.parseInt(br.readLine()));
			BasketVO addBasketVO = new BasketVO(Email, userAlChoice, userCntChoice);
			cnt += basketDAO.insertBasket(Email,addBasketVO);
		}
		System.out.println("장바구니에 " +cnt+"개의 아이템이 추가되었습니다.");
	}

}
