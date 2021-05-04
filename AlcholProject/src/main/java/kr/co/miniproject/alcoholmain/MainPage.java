package kr.co.miniproject.alcoholmain;

import java.io.IOException;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;
import kr.co.miniproject.orders.OrdersMain;
import kr.co.miniproject.shopping.AlcoholMain;
import kr.co.miniproject.shopping.BasketMain;
import kr.co.miniproject.users.MemberMain;
import kr.co.miniproject.users.MemberVO;
import kr.co.miniproject.orders.OrdersMain;

public class MainPage {
	public static void main(String[] args) {
		MemberMain membersMain = new MemberMain();
		OrdersMain ordersMain = new OrdersMain();
		AlcoholMain alcoholMain = new AlcoholMain();
		BasketMain basketMain = new BasketMain();
		MenuScreen menu = new MenuScreen();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			menu.mainScreen();
			int select = scanner.nextInt();
			// 회원가입 페이지로 이동 -> MemberMain.signUp()
			if (select == 1) {
				try {
					membersMain.signUp();
				} catch (IOException e) {
					System.out.println("회원가입중 오류가 발생하였습니다. 다시 실행해주세요.");
					System.out.println("문제가 지속적으로 발생하면 고객센터에 문의해주세요.");
					e.printStackTrace();
				}
			} else if (select == 2) {
				try {
					MemberVO loginMember = membersMain.login();
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
								// 주류목록 확인 -> alcoholMain,DAO
								alcoholMain.alcohol(loginMember.getId_email());
								break;
							case 2:
								// 장바구니 확인 -> basketMain, DAO
								basketMain.basket(loginMember.getId_email());
								break;
							case 3:
								while(true) {
									menu.mypageMessage();
									int selectMypage = scanner.nextInt();
									if(selectMypage == 1) {
										System.out.println("주문내역 확인");
										System.out.println(loginMember.getId_email());
										ordersMain.order(loginMember.getId_email());
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
							case 4 :
								System.out.println("문의하기");
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
