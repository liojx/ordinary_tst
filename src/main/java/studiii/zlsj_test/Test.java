package studiii.zlsj_test;

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


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String bb = String.format("C(%d, %d) = %d", 2, 1, 2);
		System.out.println(bb);
		String xrs = "当前挂号已成功，在您前面有%s人候诊，预计候诊时间%s。";
		String.format(xrs,"3","2018-02");
		System.out.println(String.format(xrs,"3","2018-02"));
//		pt();
//		System.out.println(isBlank("我们都有一个家名字叫中国"));
//		System.out.println(isBlank("   "));
//		System.out.println(isBlank(""));
//		System.out.println(isBlank(null));
//		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//		map.put(1, 3);
//		map.put(2, 4);
//		map.put(3, 3);
//		map.put(4, 2);
//		
//		ArrayList<Integer> ar = new ArrayList<Integer>();
//		ar.add(34);
//		ar.add(2);
//		ar.add(114);
//		ar.add(45);
//		
//		
//		for(int i = 0;i<ar.size();i++) {
//			System.out.println(ar.get(i));
//		}
//		
//		Collections.sort(ar, new Comparator<Integer>() {
//
//			@Override
//			public int compare(Integer var1, Integer var2) {
//				// TODO Auto-generated method stub
//				if(var1 < var2) {
//					return 1;
//				}else if(var1 > var2) {
//					return -1;
//				}else {
//					return 0;
//				}
//			}
//		});
//		
//		for(int i = 0;i<ar.size();i++) {
//			System.out.println(ar.get(i));
//		}
		
//		dt();
		
//		sortByValueDesc();
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
}
