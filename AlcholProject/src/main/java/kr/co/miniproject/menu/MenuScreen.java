package kr.co.miniproject.menu;

import kr.co.miniproject.users.MemberVO;

public class MenuScreen {
	public static void mainScreen() {
		System.out.println("-------- ALCOHOL 구매 서비스 --------");
		System.out.println("============== Home ==============");
		System.out.println("== 1. 회원가입   2. 로그인   3. 종료 ==");
		System.out.println("==================================");

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
		System.out.println("===================== MyPage ========================");
		System.out.println("======== 1. 주문 내역  |  2. 문의 내역  |  3. 종료 ========");
		System.out.println("=====================================================");
	}
	
	public void orderDeleteMessage() {
		System.out.println("===================== 장바구니=========================");
		System.out.println("======== 1. 주문 취소  |  2. 주문 내역  |  3. 종료 ========");
		System.out.println("=====================================================");
	}
	
	// shopping package
	public void alcoholListMessage() {
		System.out.println("======================= 주류 목록 선택 ==========================");
		System.out.println("======== 1. 탁주  |  2. 와인  |  3. 증류주  |  4. 뒤로가기 ========");
		System.out.println("==============================================================");
	}
	
	public void basketMessage() {
		System.out.println("======================= 장바구니 ==========================");
		System.out.println("======== 1. 추가 주문  |  2. 품목 삭제  |  3. 뒤로가기 ========");
		System.out.println("=========================================================");
	}
	
}
