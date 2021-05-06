package kr.co.miniproject.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;
import kr.co.miniproject.shopping.BasketVO;
import common.util.LoginMember;

public class OrdersMain {
	MenuScreen mc = new MenuScreen();
	Scanner scanner = new Scanner(System.in);
	OrdersDAO orderDAO = new OrdersDAO();
	String idEmail = LoginMember.loginId;

	public void order() {
		List<Integer> orderNumList = new ArrayList<Integer>();
		int choice = 0;
		while (true) {
			mc.orderDeleteMessage();
			int select = scanner.nextInt();
			System.out.print(">> ");
			switch (select) {
			case 1:
				orderNumList = orderDAO.addOrderNumList(idEmail);
				if (orderNumList.size() != 0) {
					System.out.println("=============== 주문 목록 ===============");
					for (int a : orderNumList) {
						System.out.println("주문번호 : " + a + "번");
					}
					System.out.println("주문번호 선택 >> ");
					choice = scanner.nextInt();
					if (orderNumList.contains(choice)) {
						List<OrdersVO> orderList = orderDAO.orderList(idEmail, choice);
						orderDAO.printOrderList(idEmail, orderList, choice);
					} else {
						System.out.println("일치하는 주문번호가 없습니다. 이전페이지로 돌아갑니다.");
					}
				} else {
					System.out.println("주문목록이 존재하지 않습니다. 이전페이지로 돌아갑니다.");
				}
				break;
			case 2:
				orderNumList = orderDAO.addOrderNumList(idEmail);
				if (orderNumList.size() != 0) {
					System.out.println("=============== 주문 목록 ===============");
					for (int a : orderNumList) {
						System.out.println("주문번호 : " + a + "번");
					}
					System.out.println("취소하실 주문 번호를 입력해주세요 >> ");
					choice = scanner.nextInt();
					if (orderNumList.contains(choice)) {
						orderDAO.orderDelete(idEmail, choice);
					} else {
						System.out.println("일치하는 주문번호가 없습니다. 이전페이지로 돌아갑니다.");
					}
				} else {
					System.out.println("주문목록이 존재하지 않습니다. 이전페이지로 돌아갑니다.");
				}
				break;
			default:
				System.out.println("이전페이지로 돌아깁니다.");
				return;
			}
		}
	}

}
