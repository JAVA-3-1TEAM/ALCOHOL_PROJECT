package common.util;

import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;
import kr.co.miniproject.mypage.MyReqOrderMain;
import kr.co.miniproject.orders.OrdersMain;
import kr.co.miniproject.shopping.AlcoholMain;
import kr.co.miniproject.shopping.BasketMain;
import kr.co.miniproject.users.MemberMain;

public class CommonImport {
	public static MemberMain membersMain = new MemberMain();
	public static OrdersMain ordersMain = new OrdersMain();
	public static AlcoholMain alcoholMain = new AlcoholMain();
	public static BasketMain basketMain = new BasketMain();
	public static MyReqOrderMain myReqOrderMain = new MyReqOrderMain();
	public static MenuScreen menu = new MenuScreen();
	public static Scanner scanner = new Scanner(System.in);
	public static LoginMember login = new LoginMember();
}
