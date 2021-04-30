package kr.co.miniproject.shopping;

import java.util.List;

public class BasketMain {
	public static void main(String[] args) {
		BasketDAO bDao = new BasketDAO();
		 	
		List<BasketVO> baList = bDao.selectBasket();
	
		System.out.println("장바구니 목록");
		
		for(BasketVO vo: baList) {
			System.out.println(vo);
		}
	}
}
