package studiii.zlsj_test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import studiii.zlsj_test.basics.classinit.This;


/**
 * @author liaosijun
 * @since 2018年9月30日 下午4:47:55
 */
public class DBUtil {
	
	static String fileName = "db.properties";
	
	static Properties loadProperty() {
		Properties pro = new Properties();
		File f = new File(This.class.getClassLoader().getResource(fileName).getPath());
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			pro.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	
	public static Connection getMySqlConnection() {
		Properties prop = loadProperty();
		String url = prop.getProperty("mysql.url");
		String user = prop.getProperty("mysql.user");
		String pwd = prop.getProperty("mysql.password");
		Connection conn= null;
	 	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
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
