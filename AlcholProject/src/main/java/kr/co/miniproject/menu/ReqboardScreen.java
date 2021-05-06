package kr.co.miniproject.menu;

import java.util.ArrayList;
import java.util.List;

import kr.co.miniproject.guestboard.ReqboardVO;

public class ReqboardScreen {

	public List<Integer> reqAll(List<ReqboardVO> rVO) {
		List<Integer> reqNumList = new ArrayList<Integer>();
		System.out.println("__________________ 문의글 목록 __________________\n");
		for (ReqboardVO vo : rVO) {
			System.out.println("________ 문의번호 "+vo.getReq_num() + "번 글. " + vo.getTitle() + " ________\n");
			System.out.println("내용 : " + vo.getContent());
			System.out.println("등록일 : " + vo.getW_date());
			System.out.println();
			reqNumList.add(vo.getReq_num());
		}
		return reqNumList;
	}
	
	public void reqComQuestion() {
		System.out.println("_______________________________________________\n");
		System.out.println("1.답변확인\n2.답변작성\n3.뒤로이동");
		System.out.println("_______________________________________________\n");
	}
	
	public void reqSelect(ReqboardVO vo) {
		System.out.println("________ 문의번호 "+vo.getReq_num() + "번 글. " + vo.getTitle() + " ________\n");
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getW_date());
		System.out.println();
	}
}
