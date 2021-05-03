package kr.co.miniproject.orders;

import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;

public class OrdersMain {
	MenuScreen mc = new MenuScreen();
	Scanner scanner = new Scanner(System.in);

	public void order(String email) {
		while (true) {
			OrdersDAO orderDAO = new OrdersDAO();
			List<OrdersVO> orderList = orderDAO.orderList(email);
			orderDAO.printOrderList(email, orderList);
			mc.orderDeleteMessage();
			int select = scanner.nextInt();
			if (select == 1) {
				System.out.println("취소하실 주문 번호를 입력해주세요 >> ");
				int choice = scanner.nextInt();
				orderDAO.orderDelete(email, choice);
				orderDAO.orderList(email);
			} else if (select == 2) {
				orderDAO.orderList(email);
			} else {
				return;
			}
		}
	}

}
