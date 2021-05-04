package kr.co.miniproject.mypage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kr.co.miniproject.users.MemberDAO;
import kr.co.miniproject.users.MemberVO;

public class ChgPwdTestClass {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//비밀번호 변경하는 메서드
	public void chgPwd() throws IOException {
		MemberDAO dao = new MemberDAO();
		String idEmail = "abc@gmail.com";
		System.out.println("변경할 비밀번호: ");
		String chgpwd = br.readLine();
		MemberVO membervo = new MemberVO(idEmail, chgpwd);
		int cnt = dao.updatePwd(membervo);		
	}
}
