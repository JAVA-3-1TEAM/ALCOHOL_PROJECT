package kr.co.miniproject.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class BasketMain {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Scanner scanner = new Scanner(System.in);
		BasketDAO bDao = new BasketDAO();

		// basketdelete()메서드 만들기 - 장바구니에서 삭제를 원할 때
		System.out.println("주문을 하시겠습니까?");
		System.out.println("1. 주문하기 2. 장바구니 삭제");
		int userChoice = scanner.nextInt();

		while (true) {
			if (userChoice == 1) {
				System.out.println("주문 페이지로 넘어갑니다.");
				break;
			} else if (userChoice == 2) {
				System.out.println("삭제할 상품번호 입력: ");

				int delal_id = Integer.parseInt(br.readLine());

				BasketVO basketVO = new BasketVO(delal_id);
				int cnt = bDao.deletebasket(basketVO);
				System.out.println("장바구니 목록에서 " + cnt + "건이 삭제되었습니다.");
				break;
			} else {
				System.out.println("유효하지 않은 번호입니다. 다시 선택해주세요.");
			}

			List<BasketVO> basketList = bDao.basketList("test1");
			bDao.printOrderList("test1", basketList);

		}
	}

}