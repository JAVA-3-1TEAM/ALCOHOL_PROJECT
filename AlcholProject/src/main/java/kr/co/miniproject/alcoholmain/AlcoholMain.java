package kr.co.miniproject.alcoholmain;

import java.io.IOException;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;
import kr.co.miniproject.orders.OrdersMain;
import kr.co.miniproject.users.MemberMain;
import kr.co.miniproject.users.MemberVO;
import kr.co.miniproject.orders.OrdersMain;

public class AlcoholMain {
	public static void main(String[] args) {
		MemberMain members = new MemberMain();
		OrdersMain orders = new OrdersMain();
		MenuScreen menu = new MenuScreen();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			menu.mainScreen();
			int select = scanner.nextInt();
			// 회원가입 페이지로 이동 -> MemberMain.signUp()
			if (select == 1) {
				try {
					members.signUp();
				} catch (IOException e) {
					System.out.println("회원가입중 오류가 발생하였습니다. 다시 실행해주세요.");
					System.out.println("문제가 지속적으로 발생하면 고객센터에 문의해주세요.");
					e.printStackTrace();
				}
			} else if (select == 2) {
				try {
					MemberVO loginMember = members.login();
					if (loginMember == null) {
						// 로그인 실패.
						menu.loginFail();
					} else {
						// 로그인 성공.
						while (true) {
							menu.loginSuccess(loginMember);
							int selectBasket = scanner.nextInt();
							switch (selectBasket) {
							case 1:
								break;
							case 2:
								System.out.println("order");
								orders.order(loginMember.getId_email());
								break;
							case 3:
								while(true) {
									menu.mypageMessage();
									int selectMypage = scanner.nextInt();
									if(selectMypage == 1) {
										
									} else if (selectMypage == 2) {
										System.out.println("문의사항 게시");
									} else if(selectMypage == 3) {
										System.out.println("메뉴로 돌아갑니다.");
										break;
									} else {
										System.out.println("유효하지 않은 번호입니다. 다시 선택해주세요.");
									}
								}
								break;
							default:
								return;
							}
						}

					}

				} catch (IOException e) {
					System.out.println("로그인중 오류가 발생하였습니다. 다시 실행해주세요.");
					System.out.println("문제가 지속적으로 발생하면 고객센터에 문의해주세요.");
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
