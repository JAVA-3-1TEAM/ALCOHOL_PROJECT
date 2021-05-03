package kr.co.miniproject.shopping;
//ALCOHOL테이블
public class AlcoholOrdersVO {
	private int basket_num;
	private int al_id;
	private String al_name;
	private String al_type;
	private int al_price;
	private int cnt_number;
	
	
	public AlcoholOrdersVO(int basket_num, int al_id, String al_name, String al_type, int al_price, int cnt_number) {
		super();
		this.basket_num = basket_num;
		this.al_id = al_id;
		this.al_name = al_name;
		this.al_type = al_type;
		this.al_price = al_price;
		this.cnt_number = cnt_number;
	}
	
	
	public int getBasket_num() {
		return basket_num;
	}
	public void setBasket_num(int basket_num) {
		this.basket_num = basket_num;
	}
	public int getAl_id() {
		return al_id;
	}

	public void setAl_id(int al_id) {
		this.al_id = al_id;
	}

	public String getAl_name() {
		return al_name;
	}

	public void setAl_name(String al_name) {
		this.al_name = al_name;
	}

	public String getAl_type() {
		return al_type;
	}
	public void setAl_type(String al_type) {
		this.al_type = al_type;
	}

	public int getAl_price() {
		return al_price;
	}
	public void setAl_price(int al_price) {
		this.al_price = al_price;
	}
	public int getCnt_number() {
		return cnt_number;
	}

	public void setCnt_number(int cnt_number) {
		this.cnt_number = cnt_number;
	}
	@Override
	public String toString() {
		return "<"+basket_num+">"+ "\n상품번호: "+al_id+
				"\n상품명: "+al_name+
				"\n종류: "+al_type+
				"\n상품가격: "+al_price+
				"\n수량: "+cnt_number+"\n--------------------------";
	}
	
	
	
	
}
