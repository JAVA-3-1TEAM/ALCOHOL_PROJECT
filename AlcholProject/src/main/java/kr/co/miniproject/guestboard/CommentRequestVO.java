package kr.co.miniproject.guestboard;

public class CommentRequestVO {
	//req_num, com_num, content, id_email, date,
	private int reqNum;
	private int comNum;
	private String title;
	private String rContent;
	private String cContent;
	String idEmail;
	String wDate;

	public CommentRequestVO(int reqNum, int comNum, String title, String rContent, String cContent, String idEmail,
			String wDate) {
		super();
		this.reqNum = reqNum;
		this.comNum = comNum; 
		this.title = title;
		this.rContent = rContent;
		this.cContent = cContent;
		this.idEmail = idEmail;
		this.wDate = wDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getwDate() {
		return wDate;
	}

	public void setwDate(String wDate) {
		this.wDate = wDate;
	}

	public int getReqNum() {
		return reqNum;
	}

	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}

	public int getComNum() {
		return comNum;
	}

	public void setComNum(int comNum) {
		this.comNum = comNum;
	}

	
	public String getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}

	public String getDate() {
		return wDate;
	}

	public void setDate(String date) {
		this.wDate = wDate;
	}

	@Override
	public String toString() {
		return "문의번호: "+reqNum+
				", 작성자: "+idEmail+", 문의접수일: "+wDate+
				"\n문의제목: "+
				"\n문의내용: "+rContent+"\n---------------------------------------"+
				"\n답글번호: "+comNum+"\n문의답글: "+cContent
				;
		
	}

}
