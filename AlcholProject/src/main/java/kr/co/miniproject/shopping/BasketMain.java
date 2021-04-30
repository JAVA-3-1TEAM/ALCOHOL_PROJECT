package kr.co.miniproject.shopping;

import java.util.List;

public class BasketMain {
	public static void main(String[] args) {
		BasketDAO bDao = new BasketDAO();
		
		
		String id_email = "abc@gmail.com";
		
		String name = bDao.userBasket(id_email);
		System.out.println(name+"님 장바구니");
		System.out.println("");
		System.out.println("<장바구니 목록>");
		
		List<AlcoholOrdersVO> aoList = bDao.basketalcohol();
		for(AlcoholOrdersVO aovo : aoList) {
			System.out.println(aovo);
		}
		
		
		
	}
	 
	
}
