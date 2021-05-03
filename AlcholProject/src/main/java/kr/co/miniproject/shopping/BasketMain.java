package kr.co.miniproject.shopping;

import java.util.List;

import kr.co.miniproject.orders.OrdersDAO;
import kr.co.miniproject.orders.OrdersVO;

public class BasketMain {
	public static void main(String[] args) {
		BasketDAO bDao = new BasketDAO();
		
		
		OrdersDAO orderDAO = new OrdersDAO();
		List<BasketVO> basketList = bDao.basketList("test1");
		bDao.printOrderList("test1", basketList);	
	}
}
