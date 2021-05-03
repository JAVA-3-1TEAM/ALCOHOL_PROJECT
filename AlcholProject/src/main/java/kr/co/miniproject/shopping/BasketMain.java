package kr.co.miniproject.shopping;

import java.util.List;

public class BasketMain {
	public static void main(String[] args) {
		BasketDAO bDao = new BasketDAO();

		List<BasketVO> basketList = bDao.basketList("test1");
		bDao.printOrderList("test1", basketList);
	}
}
