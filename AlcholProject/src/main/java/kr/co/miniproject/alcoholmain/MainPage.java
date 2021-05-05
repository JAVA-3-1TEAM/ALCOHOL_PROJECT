package kr.co.miniproject.alcoholmain;

import java.io.IOException;

import java.util.Scanner;

import common.util.LoginMember;
import kr.co.miniproject.menu.MenuScreen;
import kr.co.miniproject.users.MemberMain;
import kr.co.miniproject.users.MemberVO;
public class MainPage {
	public static void main(String[] args) {
		MemberMain membersMain = new MemberMain();
		MenuScreen menu = new MenuScreen();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			MenuScreen.mainScreen();
			int select = scanner.nextInt();
			// 회원가입 페이지로 이동 -> MemberMain.signUp()
			if (select == 1) {
				try {
					membersMain.signUp();
				} catch (IOException e) {
					MenuScreen.loginErr();
					e.printStackTrace();
				}
			} else if (select == 2) {
				try {
					MemberVO loginMember = membersMain.login();
					// 로그인 실패
					if (loginMember == null) {
						menu.loginFail();
					} else {
						// 로그인 성공.
						// 모든 페이지에서 로그인된 아이디 사용.
						LoginMember.loginId = loginMember.getId_email();
						MainPageService.mainPrint(loginMember);
					}

				} catch (IOException e) {
					MenuScreen.loginErr();
					e.printStackTrace();
				}
			} else if (select == 3) {
				System.out.println("종료 창");
				break;
			} else {
				System.out.println("유효하지 않은 번호입니다. 다시 선택해주세요.");
			}
		}
	}
}
