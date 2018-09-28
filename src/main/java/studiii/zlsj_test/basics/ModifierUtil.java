package studiii.zlsj_test.basics;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Cm n
	 * 
	 */
	static List<int[]> luan(int[] arr) {
		if(arr == null) return null;
		int len = arr.length;
		List<int[]> list = new ArrayList<int[]>();
		for(int i = 0;i<len;i++) {
			System.out.println(arr[i]);
		}
		return list;
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
		int[] oo = {1,2,3};
		ModifierUtil.luan(oo);
	}
}
