package kr.co.miniproject.mypage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.guestboard.CommentVO;
import kr.co.miniproject.menu.MenuScreen;
import common.util.LoginMember;
import kr.co.miniproject.orders.OrdersMain;

public class MyReqOrderMain {
	Scanner scanner = new Scanner(System.in);
	OrdersMain ordersMain = new OrdersMain();

	public void myReqMain() throws IOException {
		MenuScreen mc = new MenuScreen();
		String idEmail = LoginMember.loginId;
		MyReqlistDAO rdao = new MyReqlistDAO();
		while (true) {
			mc.mypageMessage();
			int selectMypage = scanner.nextInt();
			if (selectMypage == 1) {
				ordersMain.order();
			} else if (selectMypage == 2) {
				System.out.println(
						"==========================================================================================");
				System.out.println("============= 1. 내가 작성한 글 == 2. 내가 작성한 댓글 == 3. 비밀번호 변경 == 4. 뒤로가기 =============");
				System.out.println(
						"==========================================================================================");
				int select = scanner.nextInt();
				rdao.selectMyMenu(select, idEmail);
			} else if (selectMypage == 3) {
				System.out.println("메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("유효하지 않은 번호입니다. 다시 선택해주세요.");
			}
		}
	}
}
