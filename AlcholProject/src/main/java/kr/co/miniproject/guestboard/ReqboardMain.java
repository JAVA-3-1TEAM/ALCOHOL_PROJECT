package kr.co.miniproject.guestboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import common.util.LoginMember;
import kr.co.miniproject.menu.MenuScreen;

public class ReqboardMain {
	// BufferedReaderr이용하기
	static Scanner scanner = new Scanner(System.in);
	static ReqboardDAO reqDao = new ReqboardDAO();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static CommentDAO comDao = new CommentDAO();
	static MenuScreen mc = new MenuScreen();

	public static void writeNewReqOrCom() throws IOException {
		String idEmail = LoginMember.loginId;
		while (true) {
			mc.reqBoardMessage();
			int guestAnswer = scanner.nextInt();
			List<Integer> reqNumList = new ArrayList<Integer>();

			if (guestAnswer == 1) {
				while (true) {
					List<ReqboardVO> reqlistVO = reqDao.selectAllReq();
					System.out.println("<문의글 목록>");
					for (ReqboardVO vo : reqlistVO) {
						System.out.println(vo);
						reqNumList.add(vo.getReq_num());
					}
					System.out.println("1. 답변확인  2. 답변작성  3. 뒤로이동");
					int choice = scanner.nextInt();
					if (choice == 1) {
						System.out.println("답글을 보실 글 번호를 선택해주세요.");
						int selectComment = scanner.nextInt();
						if (reqNumList.contains(selectComment)) {
							for(ReqboardVO vo : reqlistVO) {
								if(vo.getReq_num() == selectComment) {
									System.out.println(vo);
								}
							}
							reqDao.showReqAndCom(selectComment);
						} else {
							System.out.println("선택하신 글 번호는 존재하지 않습니다.");
							System.out.println("이전페이지로 이동합니다.");
						}
					}
					else if(choice == 2) {
						System.out.println("답글을 입력할 글 번호를 선택해주세요.");
						int answer = scanner.nextInt();
						while (reqNumList.contains(answer) == false) {
							System.out.println("선택하신 글이 없습니다. 다시 선택해주세요.");
							System.out.print(">> ");
							answer = scanner.nextInt();
						}

						if (reqNumList.contains(answer)) {
							System.out.println("답글내용: ");
							System.out.print(">> ");
							String content = br.readLine();
							CommentVO comVO = new CommentVO(answer, content, idEmail);
							int cnt = comDao.insertOne(comVO);
							System.out.println("답글 등록이 완료되었습니다.");
							break;
						}
					} else {
						System.out.println("이전페이지로 이동합니다.");
						break;
					}

					System.out.println();
				}

			} else if (guestAnswer == 2) {
				System.out.println("==========문의글 작성하기==========");
				System.out.print("제목: ");
				String title = br.readLine();
				System.out.print("내용 입력: ");
				String content = br.readLine();

				ReqboardVO reqVO = new ReqboardVO(title, content, idEmail);

				// cnt객체에 받아서 되는건지 한 번 확인해보자.
				int cnt = reqDao.insertReq(reqVO);
				System.out.println("확인용 sys문" + cnt);

			} else if (guestAnswer == 3) {
				System.out.println("메인으로 돌아갑니다. ");
				return;
			} else {
				System.out.println("잘못된 입력입니다. 다시 선택해주세요");
			}

		}
	}

}
