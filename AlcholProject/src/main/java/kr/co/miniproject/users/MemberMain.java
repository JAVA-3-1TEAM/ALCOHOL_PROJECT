package kr.co.miniproject.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MemberMain {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	MemberDAO dao = new MemberDAO();

	// throws 처리로 BufferedReader에서 발생한 에러 상위 메서드에서 처리 --> AlcoholMain에서 처리.
	public void signUp() throws IOException {
		System.out.println("회원가입을 진행합니다.");
		System.out.println("이메일 입력 : ");
		String email = br.readLine();
		if(!dao.overlabId(email)) {
			System.out.println("중복된 아이디입니다. 이전 페이지로 이동합니다.");
			return;
		}
		System.out.println("패스워드 입력 : ");
		String pwd = br.readLine();
		System.out.println("이름 입력 : ");
		String name = br.readLine();
		System.out.println("생년월일 입력(19990101 형식으로 입력해주세요.) : ");
		String birth = br.readLine();
		if (dao.checkBirth(birth)) {
			System.out.println("전화번호 입력 : ");
			String phone = br.readLine();
			MemberVO mvo = new MemberVO(email, pwd, name, birth, phone);
			dao.insertOne(mvo);
		} else {
			System.out.println("미성년자는 회원가입이 불가능합니다.");
			System.out.println("프로그램을 종료합니다.");
		}

	}

	public MemberVO login() throws IOException {
		System.out.println("아이디 입력 : ");
		String email = br.readLine();
		System.out.println("패스워드 입력 : ");
		String pwd = br.readLine();
		MemberVO mvo = dao.selectOne(email, pwd);
		return mvo;
	}
}
