package studiii.zlsj_test.cdBankPeriod.temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestNe {
	
	public static void main(String[] args) {
//		TestNe.patt();
//		TestNe.txc();
		TestNe.txt();
	}
	
	static void patt(){
		Pattern p = Pattern.compile("^\\d+$");
		Matcher m = p.matcher("123432412");
		System.out.println(m.matches());
	}
	
	static void txc(){
		String ani = "95231202362";
		Pattern p = Pattern.compile("^[1][0-9]{10}$");
		Matcher m = p.matcher(ani);
		boolean flag = m.matches();
		System.out.println(flag);
	}
	
	static void txt(){
		String xx = "name=ÕÅ&xiaoming=12&orgid=45&orgname=×ÜÐÐ";
		
		String [] tx = (xx.split("&"));
		for(int i = 0 ;i<tx.length;i++){
			System.out.println(tx[i]);
		}
	}
}
