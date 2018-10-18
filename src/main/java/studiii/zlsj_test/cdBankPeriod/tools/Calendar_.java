package studiii.zlsj_test.cdBankPeriod.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calendar_ {
	
	static void a(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			Date now = new Date();
			Date a = sdf.parse("2017-11-11");
			cal.setTime(a);
			cal2.setTime(now);
			System.out.println(cal.get(cal.DAY_OF_YEAR) - cal2.get(6)); //cal.DAY_OF_YEAR == 6
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void b(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date a = sdf.parse("2017-11-11");
			Date b = sdf2.parse("2017-11-11 00:00:00");
			Date c = sdf.parse("2017-10-09");
			long t1 = a.getTime();
			long t2 = b.getTime();
			long t3 = c.getTime();
			System.out.println((t2-t3)/1000/24/3600);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		a();
		b();
	}
}
