package kr.co.miniproject.menu;

import kr.co.miniproject.shopping.AlcoholVO;
import kr.co.miniproject.shopping.BasketVO;

public class ShoppingScreen {
	public void alcoholAll(AlcoholVO aVO) {
		System.out.println("_____________________");
		System.out.println("상품번호 : " + aVO.getAl_id());
		System.out.println("제품명 : " + aVO.getAl_name());
		System.out.println("주종 : " + aVO.getAl_type());
		System.out.println("가격 : " + aVO.getAl_price());
	}
	public void endLine() {
		System.out.println("_____________________");
	}
	
	public void BasketAll(BasketVO bVO) {
		System.out.println("_____________________");
		System.out.println("제품번호 : " +bVO.getAlId());
		System.out.println("제품명 : " + bVO.getAlName());
		System.out.println("수량 : " + bVO.getCntNumber());
		System.out.println("가격 : " + bVO.getPrice());
	}
}
