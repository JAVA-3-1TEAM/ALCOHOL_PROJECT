package kr.co.miniproject.guestboard;

import java.sql.Date;

public class ReqboardVO {
//MYPAGE에도 받아야할 문의내역 게시판
	/*해야할 것 REQ_NUM컬럼 시퀀스 생성, W_DATE는 DEFAULT DATE이기때문에 INSERT시 목록에 뺼 것.
	 * ID_EMAIL은 자동으로 가져오는 방법에 대해서 써야할지 몰라서 우선은 id_email을 넣었으나 빼도 무방
	 * comment컬럼 not null한걸 빼줘야 insert가능함. 이부분 수정할것.
	 * comment는 게시글 보기시 select해서 comment까지 볼 수 있도록 해야함. not null컬럼을 해놓으면 insert가 불가하니까! 
	 */
	private int req_num;
	private String title;
	private String content;
	private Date w_date;
	private String comments;
	private String id_email;
	
	//insert시 고객이 작성해야하는 건 제목, 내용, 작성자 아이디
	public ReqboardVO(String title, String content, String id_email) {
		super();
		this.title = title;
		this.content = content;
		this.id_email = id_email;
	}
	 

	public ReqboardVO() {//기본생성자
	}

	public int getReq_num() {
		return req_num;
	}

	public void setReq_num(int req_num) {
		this.req_num = req_num;
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

	public Date getW_date() {
		return w_date;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getId_email() {
		return id_email;
	}

	public void setId_email(String id_email) {
		this.id_email = id_email;
	}

	@Override
	public String toString() {
		return "문의글 등록이 완료되었습니다."+"\n(문의번호: "+req_num+")";
	}
	
	
	
	
	
	
	
	
}
