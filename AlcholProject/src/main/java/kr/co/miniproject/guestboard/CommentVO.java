package kr.co.miniproject.guestboard;

import java.sql.Date;

public class CommentVO {
	int comNum; //코멘트 글 고유 번호
	int reqNum; //ReqBoard테이블에서 foriegn key로 가져올 리퀘스트 번호
	String content; //코멘트 내용
	String comDate; //답글 단 날
	String idEmail; //글쓴이 아이디
	
	public CommentVO(int comNum, String content, String idEmail) {
		super();
		this.comNum = comNum;
		this.content = content;
		this.idEmail = idEmail;
	}
	public CommentVO(int comNum, int reqNum, String content, String comDate, String idEmail) {
		super();
		this.comNum = comNum;
		this.reqNum = reqNum;
		this.content = content;
		this.comDate = comDate;
		this.idEmail = idEmail;
	}
	
	
	public CommentVO(int reqNum) {
		super();
		this.reqNum = reqNum;
	}
	public int getComNum() {
		return comNum;
	}
	public void setComNum(int comNum) {
		this.comNum = comNum;
	}
	public int getReqNum() {
		return reqNum;
	}
	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComDate() {
		return comDate;
	}
	public void setComDate(String comDate) {
		this.comDate = comDate;
	}
	public String getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}
	
	



}
