package kr.co.miniproject.guestboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReqboardCommentWriteMainTest {
	// BufferedReaderr이용하기
	static Scanner scanner = new Scanner(System.in);
	static ReqboardDAO reqDao = new ReqboardDAO();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static CommentDAO comDao = new CommentDAO();

	public static void main(String[] args) throws IOException {
		System.out.println("변경할 게시글 번호: ");
		int boardnum = Integer.parseInt(br.readLine());
		System.out.println("새로운 글 내용: ");
		String newCont = br.readLine();
		ReqboardVO reqvo = new ReqboardVO(boardnum, newCont);
		int cnt = reqDao.updateReq(reqvo);
		System.out.println(cnt+"건 업데이트 완료");
	}
	
	public void showReqAndCom(){
		//한 게시글당 해당하는 답글만 뽑아보는 기능
		comDao = new CommentDAO();
		List<CommentRequestVO> crList = comDao.selectComReqAll();
		for(CommentRequestVO crvo : crList) {
			System.out.println(crvo);
		}
	}
	
	public void writeNewReqOrCom() throws IOException {
		// 문의글 목록보고 답글쓰거나 문의글 새로작성 하는메서드
		System.out.println("1. 문의글 목록 2. 새 문의글 쓰기");
		int guestAnswer = scanner.nextInt();
		while (true) {

			List<Integer> reqNumList = new ArrayList<Integer>();

			if (guestAnswer == 1) {

				List<ReqboardVO> reqlistVO = reqDao.selectAllReq();
				System.out.println("<문의글 목록>");
				for (ReqboardVO vo : reqlistVO) {
					System.out.println(vo);

					reqNumList.add(vo.getReq_num());
				}

				System.out.println("답글을 작성하시겠습니까?");
				System.out.print("1. 네 2. 아니오(종료)");
				int reqA = scanner.nextInt();

				if (reqA == 1) {
					System.out.println("답글 달 문의글 번호? ");
					int answer = scanner.nextInt();
					while (reqNumList.contains(answer) == false) {
						System.out.println("잘못된 입력값입니다. 다시 선택해주세요.");
						System.out.print(">> ");
						answer = scanner.nextInt();
					}

					if (reqNumList.contains(answer)) {
						System.out.println("=========문의답글 작성하기=========");
						// 답변내용, 글쓴이 아이디, reqNum순으로 받아오기
						System.out.println("답글내용: ");
						System.out.print(">> ");
						String content = br.readLine();
						// 작성자 아이디 받아오는 메서드 필요-> 현재 DAO에 임의로 admin@test.com으로 지정해놓음 추후에 변경 필요
						String comIdEmailTest = "admin@test.com";
						CommentVO comVO = new CommentVO(answer, content, comIdEmailTest);
						int cnt = comDao.insertOne(comVO);
						System.out.println("comment insert 확인용 sys문입니다. 등록건수: " + cnt);
						break;
					}

				} else if (reqA == 2) {
					System.out.println("여기에 뭘넣어야 할까용");
				}

			} else if (guestAnswer == 2) {
				System.out.println("==========문의글 작성하기==========");
				System.out.print("제목: ");
				String title = br.readLine();
				System.out.print("작성자(아이디): ");
				String id = br.readLine();
				System.out.print("내용 입력: ");
				String content = br.readLine();

				ReqboardVO reqVO = new ReqboardVO(title, content, id);

				// cnt객체에 받아서 되는건지 한 번 확인해보자.
				int cnt = reqDao.insertReq(reqVO);
				System.out.println("확인용 sys문" + cnt);

			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

}
