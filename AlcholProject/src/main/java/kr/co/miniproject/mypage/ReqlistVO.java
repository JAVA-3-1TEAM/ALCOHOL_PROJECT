package kr.co.miniproject.mypage;

import java.sql.Date;

public class ReqlistVO {
	//문의내역 보여주기
	/*
	 * SELECT R.W_DATE AS "문의날짜", M.NAME AS "작성자", 
       R.TITLE AS "문의제목", R.CONTENT AS "문의내역", R.COMMENTS AS "관리자 답변"
	   FROM REQBOARD R, MEMBERS M
	   WHERE R.ID_EMAIL = M.ID_EMAIL
	 */
	
	private Date w_date;
	private String name;
	private String title;
	private String content;
	private String comments;
	
	public ReqlistVO(Date w_date, String name, String title, String content, String comments) {
		super();
		this.w_date = w_date;
		this.name = name;
		this.title = title;
		this.content = content;
		this.comments = comments;
	}

	public Date getW_date() {
		return w_date;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "문의날짜: "+w_date+ 
				"작성자: "+name+
				"제목: "+title+ 
				"문의내용: " +content+
				"답글: "+comments;
	}
}
