package kr.co.miniproject.shopping;

import java.util.List;
import java.util.Scanner;

public class AlcoholTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AlcoholDAO dao = new AlcoholDAO();
		System.out.println("주종입력 >> 1. 탁주 2. 와인 3. 증류주");
		int userChoice = scanner.nextInt();
		
		String al_type = "";
		
		if(userChoice == 1) {
			al_type = "탁주";
			List<AlcoholVO> alVO = dao.selectAl(al_type);
			for(AlcoholVO vo : alVO) {
				System.out.println(vo);
			}
			
		}else if(userChoice == 2){
			al_type = "와인";
			List<AlcoholVO> alVO = dao.selectAl(al_type);
			for(AlcoholVO vo : alVO) {
				System.out.println(vo);
			}
			
		}else if(userChoice == 3){
			al_type = "증류주";
			List<AlcoholVO> alVO = dao.selectAl(al_type);
			for(AlcoholVO vo : alVO) {
				System.out.println(vo);
			}
			
		}else {
			System.out.println("다 나가주세요......혼자있을래요..........");
		}
		
		
	}

}
