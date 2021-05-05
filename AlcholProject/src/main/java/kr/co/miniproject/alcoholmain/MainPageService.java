package kr.co.miniproject.alcoholmain;

import java.util.Scanner;

import common.util.LoginMember;
import kr.co.miniproject.guestboard.ReqboardMain;
import kr.co.miniproject.menu.MenuScreen;
import kr.co.miniproject.mypage.MyReqOrderMain;
import kr.co.miniproject.orders.OrdersMain;
import kr.co.miniproject.shopping.AlcoholMain;
import kr.co.miniproject.shopping.BasketMain;
import kr.co.miniproject.users.MemberMain;
import kr.co.miniproject.users.MemberVO;

public class MainPageService {
	public static MemberMain membersMain = new MemberMain();
	public static OrdersMain ordersMain = new OrdersMain();
	public static AlcoholMain alcoholMain = new AlcoholMain();
	public static BasketMain basketMain = new BasketMain();
	public static MyReqOrderMain myReqOrderMain = new MyReqOrderMain();
	public static MenuScreen menu = new MenuScreen();
	public static Scanner scanner = new Scanner(System.in);

	public static void mainPrint(MemberVO loginMember) {
		while (true) {
			menu.loginSuccess(loginMember);
			int selectBasket = scanner.nextInt();
			try {
				switch (selectBasket) {
				case 1:
					// 주류목록 확인 -> alcoholMain,DAO
					alcoholMain.alcohol();
					break;
				case 2:
					// 장바구니 확인 -> basketMain, DAO
					basketMain.basket();
					break;
				case 3:
					myReqOrderMain.myReqMain();
					break;
				case 4:
					System.out.println("문의하기");
					ReqboardMain.writeNewReqOrCom();
					break;
				case 5:
					LoginMember.loginId=null;
					System.out.println("성공적으로 로그아웃했습니다.");
					return;
				default:
					System.out.println("다시 입력해주세요.");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
