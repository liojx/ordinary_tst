package studiii.zlsj_test.jvm;


/**
 * @author liaosijun
 * @since 2019年1月10日 下午5:19:15
 */
public class InternT {
	/**
	 * 第一个true
	 * 第二个false
	 * 第一个等价
	 *  String a = "计算机";
	 *  String b = "软件";
	 *  String c = new StringBuilder(a).append(b).toString();
	 *  由于StringBuilder只是在原来的地址上更改值，并且intern 从1.6 首次遇到的字符串，就拷贝一份到永久代，返回永久代的字符串引用。new 出来是heap中指向的引用，intern 是永久代的引用，非同一个，所以1.6是false
	 *  1.7 的intern 是返回首次出现的实例的引用，计算机软件的intern 是首次，所以第一个是true ,而java这个字符不是首次出现的实例，所以是false，这里为什么不是首次
	 *  --> 虚拟机会调用System  --> 调用initializeSystemClass  -->Version对象的init静态方法
	 *  sun.misc.Version 对象的静态常量如下:
	 *  	private static final String launcher_name = "java";
			private static final String java_version = "1.8.0_131";
			private static final String java_runtime_name = "Java(TM) SE Runtime Environment";
			private static final String java_profile_name = "";
			private static final String java_runtime_version = "1.8.0_131-b11";
	 *  	
	 * @param args
	 */
	public static void main(String[] args) {
		String  str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		
		t();
	}
	
	/**
	 * 这里第一个是true ,第二个是false
	 * 第一个 "rrtt" 首次出现在堆中，所以z 是堆中该字符串的引用，z.intern 是首次出现的
	 * 第二个 "rrtt" 首次出现在字符串常量池中，所以k.intern 发现常量池已经有了，所以非首次
	 */
	static void t() {
		String x = "rr";
		String y = "tt";
		String z = new StringBuilder(x).append(y).toString();
		System.out.println(z.intern() == z);
		
		String j = "rrtt";
		String k = new StringBuilder(j).toString();
		System.out.println(k.intern() == k);
	}
}
