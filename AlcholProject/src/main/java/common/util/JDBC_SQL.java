package common.util;

public class JDBC_SQL {
	public static String signUp() {
		return "INSERT INTO MEMBERS (ID_EMAIL, PWD, NAME, BIRTH, PHONE) VALUES (?,?,?,?,?)";
	}
}
