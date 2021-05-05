package kr.co.miniproject.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.menu.MenuScreen;
import common.util.LoginMember;

public class AlcoholMain {
	public static Scanner scanner = new Scanner(System.in);
	public static AlcoholDAO alDAO = new AlcoholDAO();

	public void alcohol() throws IOException {
		MenuScreen ms = new MenuScreen();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<AlcoholVO> alList = null;
		String idEmail = LoginMember.loginId;
		while (true) {
			ms.alcoholListMessage();
			int userChoice = scanner.nextInt();

			String al_type = "";

			if (userChoice == 1) {
				al_type = "탁주";
				selectAlType(idEmail, al_type);
			} else if (userChoice == 2) {
				al_type = "와인";
				selectAlType(idEmail, al_type);
			} else if (userChoice == 3) {
				al_type = "증류주";
				selectAlType(idEmail, al_type);
			} else if (userChoice == 4) {
				System.out.println("뒤로가기.");
				break;
			} else {
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}

	public void selectAlType(String idEmail, String al_type) {
		try {
			List<AlcoholVO> alList = alDAO.alcoholList(al_type);
			alDAO.basketAdd(idEmail, alList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
