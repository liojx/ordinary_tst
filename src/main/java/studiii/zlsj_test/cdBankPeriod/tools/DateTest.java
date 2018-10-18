package studiii.zlsj_test.cdBankPeriod.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		long now = cal.getTimeInMillis();
		long longlongago = 0L;
		Date date =  new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD");
		try {
			Date date1970 = sdf.parse("1970-01-01");
			longlongago = date.getTime() - date1970.getTime();
			System.out.println((longlongago - now)/(1000*3600));//为什么有8个小时的时差，是因为在东8区吗
			System.out.println(longlongago- now);
			System.out.println(8*3600*1000);
			System.out.println(cal.getMaximum(Calendar.AM));
			System.out.println(cal.getMaximum(Calendar.APRIL));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
