package studiii.zlsj_test.cdBankPeriod.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBtool {
	
	private static String url = "jdbc:oracle:thin:@111.111.240.85:1521:CSRUAT";
	
	private static String user = "jjjjj";
	
	private static String pwd = "jjjjj";
	
	private static String url2 = "jdbc:oracle:thin:@localhost:1521:XE";
	
	private static String user2 = "LIAOSJ";
	
	private static String pwd2 = "liaosj";
	
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url2,user2,pwd2);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
