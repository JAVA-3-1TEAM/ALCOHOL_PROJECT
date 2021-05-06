package kr.co.miniproject.menu;

import java.util.List;

import kr.co.miniproject.orders.OrdersVO;

public class OrdersScreen {
	public void ordersAll(List<OrdersVO> orderList, int totalPrice) {
		OrdersVO oVO = orderList.get(0);
		System.out.println(oVO.getName() + "님(" + oVO.getIdEmail() + ")의 주문하신 목록입니다.\n");
		System.out.println("______________________________");
		System.out.println("주문번호 : " + oVO.getOrderNum());
		System.out.println("연락처 : " + oVO.getPhone());
		System.out.println("주소 : " + oVO.getAddress());
		System.out.println("주문날짜 : " + oVO.getOrgdate());
		for (OrdersVO o : orderList) {
			System.out.println("__ __ __ __ __ __ __ __ __ __");
			System.out.println("품명 : " +o.getAlName());
			System.out.println("주문수량 : " + o.getCntNumber());
			System.out.println("금액 : " + o.getPrice());
		}
		System.out.println("_____________________________");
		System.out.println("총 금액 : " + totalPrice);
		System.out.println("주문이 완료되었습니다.");
		System.out.println("______________________________");
	}
}
