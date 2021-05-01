package kr.co.miniproject.mypage;

import java.util.List;
import java.util.Scanner;

public class ReqOrderMainTest {
	public static void main(String[] args) {
		
		ReqlistDAO rdao = new ReqlistDAO();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("원하시는 목록을 선택해주세요.");
		System.out.println("1. 주문내역 2. 문의내역");
		System.out.print(">> ");
		int userChoice = scanner.nextInt();
		
		if(userChoice == 1) {
			//주문내욕 보여주는거 아직 못짜서 못넣음..
		}else {
			//2번 문의내역을 선택했을때, 문의내역만 보여주기
			List<ReqlistVO> reqList = rdao.requestList();
			for(ReqlistVO rvo : reqList) {
				System.out.println(rvo);
			}						
		}		
	}
}
