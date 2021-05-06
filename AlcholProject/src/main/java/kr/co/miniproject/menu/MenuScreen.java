package kr.co.miniproject.menu;

import kr.co.miniproject.users.MemberVO;

public class MenuScreen {
	// 로그인 에러 메세지
	public static void loginErr() {
		System.out.println("회원가입중 오류가 발생하였습니다. 다시 실행해주세요.");
		System.out.println("문제가 지속적으로 발생하면 고객센터에 문의해주세요.");
	}
	
	public static void mainScreen() {
		System.out.println("======== ALCOHOL 구매 서비스 ========\n");
		System.out.println("______________ Home ______________\n");
		System.out.println("1. 회원가입\n2. 로그인\n3. 종료");
		System.out.println("__________________________________\n");

	}
	
	public void loginSuccess(MemberVO mvo) {
		System.out.println("___________________________________ Main ___________________________________\n");
		System.out.println(mvo.getName() + "님 환영합니다.~~");
		System.out.println("1. 주류 목록 확인\n2. 장바구니 확인\n3. MYPAGE\n4. 문의하기\n5. 로그 아웃\n");
		System.out.println("____________________________________________________________________________\n");
	}
	
	public void loginFail() {
		System.out.println("_____________________ 로그인 실패 _____________________\n");
		System.out.println("로그인에 실패했습니다. 아이디 비밀번호를 확인해주세요.");
		System.out.println("회원이 아니시라면 회원가입을 해주세요.");
		System.out.println("메뉴로 이동합니다.");
		System.out.println("____________________________________________________\n");
	}
	
	public void mypageMessage() {
		System.out.println("______________________________ MyPage ______________________________\n");
		System.out.println("1. 주문\n2. 나의 문의 내역 & 비밀번호 변경\n3. 종료");
		System.out.println("____________________________________________________________________\n");
	}
	
	public void orderDeleteMessage() {
		System.out.println("____________________________ 주문 ____________________________\n");
		System.out.println("1. 주문 내역\n2. 주문 취소\n3. 종료");
		System.out.println("_____________________________________________________________\n");
	}
	
	// shopping package
	public void alcoholListMessage() {
		System.out.println("_________________________주류 목록 선택_________________________\n");
		System.out.println("1. 탁주\n2. 와인\n3. 증류주\n4. 뒤로가기");
		System.out.println("_____________________________________________________________\n");
	}
	
	public void basketMessage() {
		System.out.println("_______________________ 장바구니 _______________________\n");
		System.out.println("1. 추가 주문\n2. 품목 삭제\n3. 주문하기\n4. 뒤로가기");
		System.out.println("______________________________________________________\n");
	}
	
	// guestboard
	public void reqBoardMessage() {
		System.out.println("_______________________ 문의게시판 _______________________\n");
		System.out.println("1. 문의글 목록\n2. 새 문의글 쓰기\n3. 돌아가기");
		System.out.println("_______________________________________________________");

	}
	
}
