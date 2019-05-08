package studiii.zlsj_test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年8月29日 下午5:45:19
 * Modified By:
 */
public class Test {


	static void pt(){
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("1");
		ar.add("c");
		ar.add("2");
		ar.add("clm");
		Object[] br = (Object[]) ar.toArray();
		for (int i = 0; i < br.length; i++) {
			System.out.println(br[i]);
		}

		ArrayList<Long> ar2 = new ArrayList<Long>();
		ar2.add(1L);
		ar2.add(2L);
		ar2.add(3L);
//		Long[] cr = (Long[]) ar2.toArray();
		Long[] cr = ar2.toArray(new Long[0]);
		for (Long l: cr) {
			System.out.println(l);
		}
	}

	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}



	private static void getTI() {
		long l = 1555054470576l;
		Date d = new Date(l);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
	}
	private static  void sortByValueDesc(){
        Map<Integer,Integer> tm=new TreeMap<Integer,Integer>();
        tm.put(1, 4);   tm.put(2, 5);
        tm.put(3, 4);   tm.put(4, 2);
         //这里将map.entrySet()转换成list
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(tm.entrySet());
		// 然后通过比较器来实现排序
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			// 降序排序
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Map.Entry<Integer, Integer> mapping : list) {
			 System.out.println(mapping.getKey()+":"+mapping.getValue());
		}
    }
	
	static void dt() {
//		MonsterDO monsterDO = extMonsterDOMapper.findMonstersByMonsterId(battleMonsterVo.getMonsterId());
		Map<Integer, Integer> monsterAttMap = new TreeMap<Integer, Integer>();
		monsterAttMap.put(1, 3);
		monsterAttMap.put(2, 4);
		monsterAttMap.put(3, 6);
		monsterAttMap.put(4, 2);
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(monsterAttMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
               if(o1.getValue() > o2.getValue()) {
            	   return 1;
               }else if (o1.getValue() < o2.getValue()) {
            	   return -1;
               }else {
            	   return 0;
               }
            }
        });
		monsterAttMap.keySet().forEach(abc -> {
			System.out.println(monsterAttMap.put(abc, monsterAttMap.get(abc) + 2));
		});
		monsterAttMap.keySet().forEach(abc -> {
			System.out.println(monsterAttMap.get(abc));
		});
	}
	
	void ab() {
		System.out.println(12);
		System.out.println(122);
		System.out.println(12);
	}
	

	static void minusTime(Date now, String befor){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date queryStartDate = simpleDateFormat.parse(befor);
			long year = 365 * 24 * 3600 * 1000L;
			long day = 24 * 3600 * 1000L;
			System.out.println((now.getTime() - queryStartDate.getTime())/day);
			System.out.println((now.getTime() - queryStartDate.getTime()) -year );
		} catch (ParseException e) {

		}
	}


	static void  yanshi(String dateS){
		/**
		 * 白天 8:30 - 20:00
		 */
//		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf3.parse(dateS);
		} catch (ParseException e) {

		}
		String pre = sdf2.format(date);
		String dayOrNight =null;
		try {
			Date dateBegin = sdf3.parse(pre + " 08:30");
			Date dateEnd = sdf3.parse(pre + " 20:00");
			if(date.after(dateBegin) && date.before(dateEnd)){
				dayOrNight = "d";
			}else{
				dayOrNight = "n";
			}
			System.out.println("当前dayOrNight_1标志：" + dayOrNight);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void decInt(){
		BigDecimal  ab = new BigDecimal(12);
		Integer cc = new Integer(123);
		Map<String, Object> map = new TreeMap<>();
		map.put("key",cc);
		BigDecimal ff = BigDecimal.valueOf((Integer)(map.get("key")));
		System.out.println(ff);

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		dt();
//		sortByValueDesc();
//		getTI();
//		minusTime(new Date(),"2018-05-08 09:52:30");
//		yanshi("2019-05-08 19:59");
		decInt();
	}
}
