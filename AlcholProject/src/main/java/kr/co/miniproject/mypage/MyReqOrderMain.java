package kr.co.miniproject.mypage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import kr.co.miniproject.guestboard.CommentVO;

public class MyReqOrderMain {
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		MyReqlistDAO rdao = new MyReqlistDAO();

//		int userChoice = scanner.nextInt();
		String idEmail = "test1";
//		rdao.myRequestDelete(idEmail, 1);
//		rdao.myRequestList(idEmail);
		rdao.myCommentsList(idEmail);
		rdao.myCommentDelete(idEmail, 3);
		rdao.myCommentsList(idEmail);

	}

	public void myReqMain(String Email) throws IOException {
		MyReqlistDAO rdao = new MyReqlistDAO();

		System.out
				.println("==========================================================================================");
		System.out.println("============= 1. 내가 작성한 글 == 2. 내가 작성한 댓글 == 3. 비밀번호 변경 == 4. 뒤로가기 =============");
		System.out
				.println("==========================================================================================");
		int select = scanner.nextInt();
		switch (select) {
		case 1:
			while (true) {
				List<MyReqlistVO> reqList = rdao.myRequestList(Email);
				System.out.println("삭제하실 글의 번호를 입력해주세요. 뒤로가기는 0번을 입력하세요.");
				int choice = scanner.nextInt();
				if (choice != 0) {
					rdao.myRequestDelete(Email, choice);
					System.out.println("글이 삭제되었습니다.");
				} else {
					break;
				}
			}
			break;
		case 2:
			while (true) {
				List<CommentVO> comList = rdao.myCommentsList(Email);
				System.out.println("삭제하실 댓글의 번호를 입력해주세요. 뒤로가기는 0번을 입력하세요.");
				int choice = scanner.nextInt();
				if (choice != 0) {
					rdao.myCommentDelete(Email, choice);
					System.out.println("댓글이 삭제되었습니다.");
				} else {
					break;
				}
			}
			break;
		case 3:
			int result = rdao.chgPwd(Email);
			if (result != 0) {
				System.out.println("비밀번호 변경이 완료되었습니다. 이전 페이지로 이동합니다.");
			}
			break;
		case 4:
			break;
		default:
			System.out.println("입력한 값이 잘못되었습니다. 뒤로 이동합니다.");
			break;
		}
	}
}
