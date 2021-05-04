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
	private String wDate;
	private String idEmail;
	
	//insert시 고객이 작성해야하는 건 제목, 내용, 작성자 아이디
	public ReqboardVO(String title, String content, String id_email) {
		super();
		this.title = title;
		this.content = content;
		this.idEmail = id_email;
	}
	
	public ReqboardVO(int req_num) {
		this.req_num = req_num;
	}
	//글 내용 수정시 필요한 생성자
	public ReqboardVO(int req_num, String content) {
		super();
		this.req_num = req_num;
		this.content = content;
	}

	//reqboard의 모든 목록을 볼 수 있도록 하는 생성자
	public ReqboardVO(int req_num, String title, String content, String w_date, String id_email) {
		super();
		this.req_num = req_num;
		this.title = title;
		this.content = content;
		this.wDate = w_date;
		this.idEmail = id_email;
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

	public String getW_date() {
		return wDate;
	}

	public void setW_date(String w_date) {
		this.wDate = w_date;
	}

	
	public String getId_email() {
		return idEmail;
	}

	public void setId_email(String id_email) {
		this.idEmail = id_email;
	}

	@Override
	public String toString() {
		return "-----------------------------------------\n"+
				"문의번호: "+req_num+
				", 글제목: "+title+
				"\n내용: "+content+
				"\n등록일: "+wDate+" , 작성자: "+idEmail;
	}
	
	
}
