package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Collections_Map {
	
	public static void test1(){
		Map<String,String> hashmap = new HashMap<String, String>();
		hashmap.put("guangxi", "nanning");
		hashmap.put("sichuan", "chengdu");
		hashmap.put("shanghai", null);
		hashmap.put("guangxi", "yulin");
		for(String set:hashmap.keySet()){
			System.out.println(set +"  " +hashmap.get(set));
		}
			
		System.out.println(hashmap.size());
		Map<String,String> hashtable = new Hashtable<String,String>();
		hashtable.put(null, null);
		hashtable.put("xizang", "lasha");
		
	}
	
	public static void main(String[] args) {
		Collections_Map.test1();
	}
}
