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
		System.out.println("__________________________________");

	}
	
	public void loginSuccess(MemberVO mvo) {
		System.out.println("=================================== Header ===================================");
		System.out.println(mvo.getName() + "님 환영합니다.~~");
		System.out.println("==== 1. 주류 목록 확인 | 2. 장바구니 확인 | 3. MYPAGE | 4. 문의하기 | 5. 프로그램 종료 ====");
		System.out.println("==============================================================================");
	}
	
	public void loginFail() {
		System.out.println("===================== 로그인 실패 =====================");
		System.out.println("로그인에 실패했습니다. 아이디 비밀번호를 확인해주세요.");
		System.out.println("회원이 아니시라면 회원가입을 해주세요.");
		System.out.println("메뉴로 이동합니다.");
		System.out.println("====================================================");
	}
	
	public void mypageMessage() {
		System.out.println("============================ MyPage ===============================");
		System.out.println("======== 1. 주문  |  2. 나의 문의 내역 & 비밀번호 변경  |  3. 종료 ========");
		System.out.println("===================================================================");
	}
	
	public void orderDeleteMessage() {
		System.out.println("===================== 주문하기 =========================");
		System.out.println("======== 1. 주문 내역  |  2. 주문 취소 |  3. 종료 ========");
		System.out.println("=====================================================");
	}
	
	// shopping package
	public void alcoholListMessage() {
		System.out.println("======================= 주류 목록 선택 ==========================");
		System.out.println("======== 1. 탁주  |  2. 와인  |  3. 증류주  |  4. 뒤로가기 ========");
		System.out.println("==============================================================");
	}
	
	public void basketMessage() {
		System.out.println("======================= 장바구니 =======================================");
		System.out.println("======== 1. 추가 주문  |  2. 품목 삭제  |  3. 주문하기  |  4. 뒤로가기 ========");
		System.out.println("======================================================================");
	}
	
	// guestboard
	public void reqBoardMessage() {
		System.out.println("=============== 문의게시판 ===============");
		System.out.println("1. 문의글 목록 2. 새 문의글 쓰기 3. 돌아가기");
		System.out.println("=======================================");

	}
	
}
