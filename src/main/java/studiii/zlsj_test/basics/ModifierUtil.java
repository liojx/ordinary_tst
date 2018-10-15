package studiii.zlsj_test.basics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studiii.zlsj_test.util.DBUtil;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午5:36:43
 */
public class ModifierUtil {
	
	static enum  ModifierEnum{
		
		PUBLIC(1,"public"),
		PRIVATE(2,"private"),
		PROTECTED(4,"protected"),
		STATIC(8,"static"),
		FINAL(16,"final"),
		SYNCHRONIZED(32,"synchronized"),
		VOLATILE(64,"volatile"),
		TRANSIENT(128,"transient"),
		NATIVE(256,"native"),
		INTERFACE(512,"interface"),
		ABSTRACT(1024,"abstract"),
		STRICT(2048,"strict");
		
		private final int key;
		
		private final String desc;
		
		private ModifierEnum(int key, String desc) {
			this.key = key;
			this.desc = desc;
		}

		/**
		 * @return the key
		 */
		public int getKey() {
			return key;
		}

		/**
		 * @return the desc
		 */
		public String getDesc() {
			return desc;
		}
		
	}
	
	static List<int[]> C(int idx,int up,int down){
		if(up > down || up < 0 || down < 0) {
			return null;
		}
		List<int[]> result = new ArrayList<int[]>();
		if(up == 1) {
			for(int i = 1;i <= down;i++) {
				int [] every = new int[up];
				every[up - 1] = i;
				result.add(every);
			}
		}else if(up == down) {
			int [] every = new int[up];
			for(int i = 0;i < down; i++) {
				every[i] =  i+1;
			}
			result.add(every);
		}else {
			int[] every = new int[up];
			
			Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
			 for(int ac = 1; ac <= up; ac++) {
				 List<Integer> arr = new ArrayList<Integer>();
				 int indexOf = ac;
				 int cc = indexOf;
				 do {
					 arr.add(cc);
					 ++cc;
				 }while(cc <= (down-up+indexOf));// 例如C7选3，那么第1 位可以排 1到7-3+1 = 5，第2位 能排2到7-3+2=6，第3位只能排3到7-3+3=7
				 map.put(ac, arr);
			 }
			 for(int i = 0;i < every.length;i++) {
				 List<Integer> list = map.get(i+1);
				 every[i] = list.get(i);
			 }
		}
		return result;
	}
	
	
	static int[] maopao(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=arr.length-1;j>i;j--) {
				if(arr[j] < arr[i]) {
					int  temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	
	static boolean contains(List<int[]> list ,int[] arr) {
		boolean flag = false;
		for(int[] oneArr : list) {
			if(oneArr.length == arr.length) {
				oneArr = maopao(oneArr);
				arr =  maopao(arr);
				int num = 0;
				for(int i =0;i<oneArr.length;i++) {
					if(arr[i] == oneArr[i]) {
							num++;
					}
				}
				if(num == arr.length) flag = true;
			}
		}
		return flag;
	}
	
	
	
	/**
	 * 传入一个数字数组，返回一个所有可能的字符串 
	 * @param arr
	 * @return
	 */
	static String combine(int[] arr) {
		if(arr == null) return "";
		int len = arr.length;
		for(int i = 0; i < len; i++) {
			for(int j = len - 1; j > 0; j--) {
				System.out.println(arr[j]);
			}
		}
		return "";
	}
	
	static String convert(int param) {
		ModifierEnum[] mod = ModifierEnum.values();
		for(ModifierEnum one : mod) {
			System.out.println(one.getDesc());
		}
		return "";
	}
	
	public static void main(String[] args) {
//		int[] oo = {15,52,33};
//		List<int[]>  list = ModifierUtil.luan(oo);
//		for(int[] a : list) {
//			System.out.println(a.toString()+"---");
//			for(int i : a) {
//				System.out.println(i);
//			}
//		}
		
//		System.out.println(DBUtil.getMySqlConnection());
		
//		for(int o : a) {
//			System.out.println(o);
//		}
//		List<int[]> list = new ArrayList<int[]>();
//		int[] a = {1,2,4,4,6,7,8,9};
//		int[] b = {1,2,4,4,8,9};
//		int[] c = {1,2,4,5};
//		int[] e = {3,2,4,5};
//		int[] f = {3,2,4,5,5,5,5,5,5,5,5,55,6,7,7};
//		list.add(a);
//		list.add(b);
//		list.add(c);
//		list.add(e);
//		list.add(f);
//		int[] d = {3,2,4,5,5,5,5,5,5,5,5,55,6,7,7};
//		System.out.println(contains(list, d));
//		int[] a = get(4, 10);
//		for(int i :a) {
//			System.out.println(i);
//			
//		}
		
//		List<String> a = new ArrayList<String>();a.add("1");a.add("1");a.add("1");a.add("1");
//		a.removeAll(a);
//		System.out.println(a.size());
	}
}
