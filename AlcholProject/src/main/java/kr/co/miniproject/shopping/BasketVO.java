package kr.co.miniproject.shopping;

public class BasketVO {
	private int basket_num;
	private String id_email;
	private int al_id;
	private int cnt_number;
	

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
	}

	public int getBasket_num() {
		return basket_num;
	}

	public void setBasket_num(int basket_num) {
		this.basket_num = basket_num;
	}

	public String getId_email() {
		return id_email;
	}

	public void setId_email(String id_email) {
		this.id_email = id_email;
	}

	public int getAl_id() {
		return al_id;
	}

	public void setAl_id(int al_id) {
		this.al_id = al_id;
	}

	public int getCnt_number() {
		return cnt_number;
	}

	public void setCnt_number(int cnt_number) {
		this.cnt_number = cnt_number;
	}

	@Override
	public String toString() {
		return basket_num +"\n상품: "+al_id +", 수량: "+cnt_number;
	}//BasketDAO에 상품내용 가져오면 toString내용 다시 한 번 정리할 것.
	
	
	
	
	
}
