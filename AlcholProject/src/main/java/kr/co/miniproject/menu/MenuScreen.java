package kr.co.miniproject.menu;

import kr.co.miniproject.users.MemberVO;

public class MenuScreen {
	public static void mainScreen() {
		System.out.println("-------- ALCOHOL 구매 서비스 --------");
		System.out.println("==================================");
		System.out.println("== 1. 회원가입   2. 로그인   3. 종료 ==");
		System.out.println("==================================");

	}
	
	public void loginSuccess(MemberVO mvo) {
		System.out.println("==================================");
		System.out.println(mvo.getName() + "님 환영합니다.~~");
		System.out.println("==================================");
	}
	
	public void loginFail() {
		System.out.println("==================================");
		System.out.println("아이디와 패스워드가 일치하지 않습니다.");
		System.out.println("회원이 아니시라면 회원가입을 해주세요.");
		System.out.println("==================================");
	}
}
