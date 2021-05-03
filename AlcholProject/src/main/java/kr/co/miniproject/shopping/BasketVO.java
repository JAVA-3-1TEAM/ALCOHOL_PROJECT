package kr.co.miniproject.shopping;

public class BasketVO {
	String orderNum;
	String idEmail;
	String name;
	String phone;
	String address;
	String alName;
	String cntNumber;
	int price;
	String orgdate;

	public BasketVO(String orderNum, String idEmail, String name, String phone, String address, String alName, String cntNumber,
			int price, String orgdate) {
		super();
		this.orderNum = orderNum;
		this.idEmail = idEmail;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.alName = alName;
		this.cntNumber = cntNumber;
		this.price = price;
		this.orgdate = orgdate;
		}


	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	public String getIdEmail() {
		return idEmail;
	}


	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAlName() {
		return alName;
	}


	public void setAlName(String alName) {
		this.alName = alName;
	}


	public String getCntNumber() {
		return cntNumber;
	}


	public void setCntNumber(String cntNumber) {
		this.cntNumber = cntNumber;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getOrgdate() {
		return orgdate;
	}


	public void setOrgdate(String orgdate) {
		this.orgdate = orgdate;
	}


	@Override
	public String toString() {
		return "주문번호 : " + orderNum + "\n" +
			    "연락처 : " + phone + "\n" +
			    "주소 : " + address + "\n" +
			    "이름 : " + alName + "\n" +
			    "주문수량 : " + cntNumber + "\n" +
			    "금액 : " + price + "\n" +
			    "주문 날짜 : " + orgdate + "\n";
	}

	
	
	
}
