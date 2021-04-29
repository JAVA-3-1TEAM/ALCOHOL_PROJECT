package kr.co.miniproject.users;

public class MemberVO {
	private String id_email;
	private String pwd;
	private String name;
	private String birth;
	private String phone;

	public MemberVO(String id_email, String pwd, String name, String birth, String phone) {
		super();
		this.id_email = id_email;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
	}

	public String getId_email() {
		return id_email;
	}

	public void setId_email(String id_email) {
		this.id_email = id_email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
