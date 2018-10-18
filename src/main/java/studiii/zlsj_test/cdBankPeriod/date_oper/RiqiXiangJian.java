package studiii.zlsj_test.cdBankPeriod.date_oper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RiqiXiangJian {
	
	int minusdate(String date_begin,String date_end){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date begin = sdf.parse(date_begin);
			Date end = sdf.parse(date_end);
			return (int) ((end.getTime() - begin.getTime() ) /1000 /24 /3600);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	String input(){
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入日期：");
		String date = sc.next();
		return date;
	}
	
	void ret(){
		String a = input();
		String b = input();
		int r = minusdate(a,b);
		System.out.println("相减为【"+r+"】天");
	}
	
	public static void main(String[] args) {
		RiqiXiangJian r = new RiqiXiangJian();
		r.ret();
	}
}
