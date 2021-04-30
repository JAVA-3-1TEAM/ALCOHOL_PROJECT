package kr.co.miniproject.shopping;

public class AlcoholVO {
		private int al_id;
		private String al_name;
		private String al_type;
		private int al_price;
		
		public AlcoholVO(int al_id, String al_name, String al_type, int al_price) {
			super();
			this.al_id = al_id;
			this.al_name = al_name;
			this.al_type = al_type;
			this.al_price = al_price;
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
		@Override
		public String toString() {
			return "상품번호: " + al_id + ", 제품명: " + al_name + ", 주종: " + al_type + ", 가격: "
					+ al_price + "";
		}
		
		

}