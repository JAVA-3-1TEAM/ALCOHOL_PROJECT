package kr.co.miniproject.shopping;

public class BasketVO {
	int basketNum;
	String idEmail;
	String name;
	String alName;
	int alId;
	int cntNumber;
	int price;

	public BasketVO(int basketNum, String idEmail, int alId, int cntNumber) {
		super();
		this.basketNum = basketNum;
		this.idEmail = idEmail;
		this.alId = alId;
		this.cntNumber = cntNumber;
	}

	// basketAdd()에서 사용될 생성자

	public BasketVO(String idEmail, int alId, int cntNumber) {
		super();
		this.idEmail = idEmail;
		this.alId = alId;
		this.cntNumber = cntNumber;
	}

	public BasketVO(int alId) {
		super();
		this.alId = alId;
	}

	public BasketVO(int basketNum, String idEmail, String name, int alId, String alName, int cntNumber, int price) {
		super();
		this.basketNum = basketNum;
		this.idEmail = idEmail;
		this.name = name;
		this.alId = alId;
		this.alName = alName;
		this.cntNumber = cntNumber;
		this.price = price;
	}

	public int getAlId() {
		return alId;
	}

	public void setAlId(int alId) {
		this.alId = alId;
	}

	public int getBasketNum() {
		return basketNum;
	}

	public void setBasketNum(int basketNum) {
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
		return "제품번호 : " + alId + "\n" + "품목 : " + alName + "\n" + "수량 : " + cntNumber + "\n" + "금액 : " + price;
	}

}
