package kr.co.miniproject.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;

public class AlcoholMain {
	public static Scanner scanner = new Scanner(System.in);

	public void alcohol(String Email) throws IOException {
		AlcoholDAO alDAO = new AlcoholDAO();
		MenuScreen ms = new MenuScreen();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<AlcoholVO> alList = null;
		AlcoholDAO dao = new AlcoholDAO();
		while (true) {
			ms.alcoholListMessage();
			int userChoice = scanner.nextInt();

			String al_type = "";

			if (userChoice == 1) {
				al_type = "탁주";

			} else if (userChoice == 2) {
				al_type = "와인";

			} else if (userChoice == 3) {
				al_type = "증류주";
			} else if (userChoice == 4) {
				System.out.println("뒤로가기.");
				break;
			} else {
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
			alList = dao.alcoholList(al_type);
			alDAO.basketAdd(Email, alList);
		}
	}

}
