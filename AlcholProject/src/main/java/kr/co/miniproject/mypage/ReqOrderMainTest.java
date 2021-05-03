package kr.co.miniproject.mypage;

import java.util.List;
import java.util.Scanner;

public class ReqOrderMainTest {
	public static void main(String[] args) {

		ReqlistDAO rdao = new ReqlistDAO();

		Scanner scanner = new Scanner(System.in);
//		int userChoice = scanner.nextInt();
		String idEmail = "test1";
		List<ReqlistVO> reqList = rdao.myRequestList(idEmail);
	}
}
