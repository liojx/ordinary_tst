package studiii.zlsj_test;

import java.util.Calendar;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月6日 下午4:54:05
 * Modified By:
 */
public class Tset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int total = 4000;
		int pageSize = 5000;
		System.out.println("000==" + 5041/5000);
		int page = total/pageSize == 0 ? total/pageSize : total/pageSize + 1;
		System.out.println("page =======" + page);
//		// TODO Auto-generated method stub
//		Integer a = 0;
//		a = null;
////		if( a <= 0) {
////			System.out.println(1);
////		}
//		if( a == null) {
//			System.out.println(1);
//		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR,-1);
		calendar.set(Calendar.HOUR_OF_DAY,8);
		calendar.set(Calendar.MINUTE,19);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND, 0);
		Integer time = (int)((calendar.getTimeInMillis()-System.currentTimeMillis()) /1000);
		System.out.println(time);
		System.out.println(Tset.class.getResource("/").getPath());
		System.out.println(System.getProperty("user.dir"));
	}

}
