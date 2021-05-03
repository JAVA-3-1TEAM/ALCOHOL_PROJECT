package kr.co.miniproject.shopping;

public class BasketVO {
	String basketNum;
	String idEmail;
	String name;
	String alName;
	int cntNumber;
	int price;

	

	public BasketVO(int basket_num, String id_email, int al_id, int cnt_number) {
		super();
		this.basket_num = basket_num;
		this.id_email = id_email;
		this.al_id = al_id;
		this.cnt_number = cnt_number;
	}	
	
	//basketAdd()에서 사용될 생성자
	public BasketVO(String id_email, int al_id, int cnt_number) {
		super();
		this.id_email = id_email;
		this.al_id = al_id;
		this.cnt_number = cnt_number;
	}

	
	public BasketVO(int al_id) {
		super();
		this.al_id = al_id;



	public BasketVO(String basketNum, String idEmail, String name, String alName, int cntNumber, int price) {
		super();
		this.basketNum = basketNum;
		this.idEmail = idEmail;
		this.name = name;
		this.alName = alName;
		this.cntNumber = cntNumber;
		this.price = price;
	}





	public String getBasketNum() {
		return basketNum;
	}





	public void setBasketNum(String basketNum) {
		this.basketNum = basketNum;
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





	public String getAlName() {
		return alName;
	}





	public void setAlName(String alName) {
		this.alName = alName;
	}





	public int getCntNumber() {
		return cntNumber;
	}





	public void setCntNumber(int cntNumber) {
		this.cntNumber = cntNumber;
	}





	public int getPrice() {
		return price;
	}





	public void setPrice(int price) {
		this.price = price;
	}





	@Override
	public String toString() {
		return "주문번호 : " + basketNum + "\n" +
				"품목 : " + alName + "\n" +
				"수량 : " + cntNumber + "\n" +
				"금액 : " + price;
	}

	
	
	
}
