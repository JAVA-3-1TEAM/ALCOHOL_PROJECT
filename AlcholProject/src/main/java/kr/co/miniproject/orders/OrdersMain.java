package kr.co.miniproject.orders;

import java.util.List;

public class OrdersMain {
	public void order(String email) {
		OrdersDAO orderDAO = new OrdersDAO();
		List<OrdersVO> orderList = orderDAO.orderList(email);
		orderDAO.printOrderList(email, orderList);
		
	}
	
}
