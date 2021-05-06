package kr.co.miniproject.mypage;

import java.sql.Date;

public class MyReqlistVO {

	private int reqNum;
	private String title;
	private String content;
	private String name;
	private String wDate;

	public MyReqlistVO(int reqNum, String title, String content, String name, String wDate2) {
		super();
		this.reqNum = reqNum;
		this.title = title;
		this.content = content;
		this.name = name;
		this.wDate = wDate2;
	}

	public int getReqNum() {
		return reqNum;
	}

	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getwDate() { 
		return wDate;
	}

	public void setwDate(String wDate) {
		this.wDate = wDate;
	}

	@Override
	public String toString() {
		return reqNum + "번. \n" + "제목 : "  + title + "\n"
				+ "내용 : " + content + "\n"
				+ "작성자 : " + name + "\n" 
				+ "작성시간 : " + wDate;
	}
}
