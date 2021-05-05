package kr.co.miniproject.shopping;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;
import common.util.LoginMember;

public class BasketMain {
	MenuScreen ms = new MenuScreen();

	public void basket() throws IOException {
		Scanner scanner = new Scanner(System.in);
		BasketDAO bDAO = new BasketDAO();
		AlcoholDAO alDAO = new AlcoholDAO();
		String idEmail = LoginMember.loginId;

		List<BasketVO> basketList = bDAO.basketList(idEmail);
		// 유저 장바구니에 존재하는 데이터의 basketNum 저장.
		List<Integer> basketNumList = null;
		if (basketList.size() == 0) {
			System.out.println("장바구니가 비어있습니다.");
		} else {
			while (true) {
				basketNumList = bDAO.printOrderList(idEmail, basketList);
				if(basketList.size() ==0) {
					break;
				}
				ms.basketMessage();
				int select = scanner.nextInt();
				if (select == 1) {
					// 추가주문 -> 알콜리스트
					AlcoholMain alcoholMain = new AlcoholMain();
					alcoholMain.alcohol();
					// 추가된 데이터 리스트에 저장.
					basketList = bDAO.basketList(idEmail);
				} else if (select == 2) {
					System.out.println("삭제하실 품목의 주문 번호를 입력해주세요.");
					int choiceBasketNum = scanner.nextInt();
					if (basketNumList.contains(choiceBasketNum)) {
						// 객체가 담긴 리스트 탐색하면서 번호가 일치하는 객체 찾을 시 삭제.
						for (BasketVO bvo : basketList) {
							if (bvo.getAlId() == choiceBasketNum) {
								bDAO.deletebasket(choiceBasketNum);
								System.out.println("품목이 삭제되었습니다. 이전페이지로 이동합니다.");
								basketList = bDAO.basketList(idEmail);
								break;
							}
						}
					} else {
						System.out.println("일치하는 품목이 없습니다. 이전페이지로 이동합니다.");
						break;
					}

				} else if (select == 3) {
					System.out.println("주문하기로 이동합니다.");
					bDAO.insertOrderToBasket(idEmail);
					break;
				} else if(select ==4){
					// 뒤로가기
					break;
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}

			}
		}
	}
}