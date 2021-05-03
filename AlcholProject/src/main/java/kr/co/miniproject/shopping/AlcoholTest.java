package kr.co.miniproject.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class AlcoholTest {
public static Scanner scanner = new Scanner(System.in);

	
public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
		AlcoholDAO dao = new AlcoholDAO();
		
		System.out.println("주종입력 >> 1. 탁주 2. 와인 3. 증류주");
		int userChoice = scanner.nextInt();

		String al_type = "";
		
		
		if (userChoice == 1) {
			al_type = "탁주";
			List<AlcoholVO> alVO = dao.selectAl(al_type);
			for (AlcoholVO vo : alVO) {
				System.out.println(vo);
			}

		} else if (userChoice == 2) {
			al_type = "와인";
			List<AlcoholVO> alVO = dao.selectAl(al_type);
			for (AlcoholVO vo : alVO) {
				System.out.println(vo);
			}

		} else if (userChoice == 3) {
			al_type = "증류주";
			List<AlcoholVO> alVO = dao.selectAl(al_type);
			for (AlcoholVO vo : alVO) {
				System.out.println(vo);
			}
			
		}
		
		basketAdd(); 
		
	}
	
	
	//장바구니에 추가하는 메서드
	public static void basketAdd() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BasketDAO b_addDAO = new BasketDAO();
		String testId = "abc@gmail.com";

		System.out.println("장바구니에 추가를 원하는 상품번호를 입력해주세요.");
		System.out.print(">> ");
		int userAlChoice = (Integer.parseInt(br.readLine()));
		System.out.println(userAlChoice);
		//menu.loginSuccess(loginMember);이메일 값은 메서드로 넣어주고
		System.out.println("상품의 수량을 입력해주세요");	
		System.out.print(">> ");
		int userCntChoice = (Integer.parseInt(br.readLine()));
		
		BasketVO addBasketVO = new BasketVO(testId, userAlChoice, userCntChoice);
		
		int cnt = b_addDAO.insertBasket(addBasketVO);
		System.out.println("장바구니에 " +cnt+"개의 아이템이 추가되었습니다.");
	}

}
