package kr.co.miniproject.users;

public class MemberTest {
	public static void main(String[] args) {
		MemberVO mvo = new MemberVO("sss", "12324", "hanj", "940121", "010-1234-1234");
		MemberDAO dao = new MemberDAO();
		dao.insertOne(mvo);
		
	}
}
