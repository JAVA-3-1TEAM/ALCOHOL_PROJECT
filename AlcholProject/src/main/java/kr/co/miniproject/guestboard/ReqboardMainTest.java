package kr.co.miniproject.guestboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReqboardMainTest {
	//BufferedReaderr이용하기

	public static void main(String[] args) throws IOException {
		ReqboardDAO reqDao = new ReqboardDAO();
		//입력을 받아서 저장이 되는지 알지 못한다. 확인필요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("==========문의글 작성하기==========");
		System.out.print("제목: ");
		String title = br.readLine();
		System.out.print("\n작성자(아이디): ");
		String id = br.readLine();
		System.out.print("\n내용 입력: ");	
		String content = br.readLine();
		
		ReqboardVO reqVO = new ReqboardVO(title, content, id);
		
		//cnt객체에 받아서 되는건지 한 번 확인해보자.
		int cnt = reqDao.insertOne(reqVO);
		System.out.println("확인용 sys문"+cnt);
		
	
	}
	
	
	 
}
