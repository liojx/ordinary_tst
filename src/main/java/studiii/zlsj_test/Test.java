package studiii.zlsj_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.Map.Entry;


/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年8月29日 下午5:45:19
 * Modified By:
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
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
		
		dt();
		
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
}
