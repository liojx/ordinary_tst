package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.Stack;

public class StackTest {
	static String[] months ={"January","February","March","April","May",
		"June","July","August","September","October","November","December"};
	public static void main(String[] args) {
		Stack stc = new Stack();
		for(int i=0;i<months.length;i++){
			stc.push(months[i]);
		}
		System.out.println("stack = "+ stc);
		stc.addElement("The last line");
		System.out.println("element 5 = "+stc.elementAt(5));
		System.out.println("popping elements:");
		while(!stc.empty()){
			System.out.println(stc.pop());
		}
		
		StackTest.getCurr();
		StackTest.getCurr2();
		StackTest.getString("hello", "word!");
		StackTest.getString2("hello", "word!");
		
	}
	
	/**
	 * 测试String  和StringBuffer的效率
	 */
	private static void getCurr(){
		long start = System.currentTimeMillis();
		for(int i=0;i<100000000;i++){
			String str = "hello "+"world!";
		}
		long end = System.currentTimeMillis();
		System.out.println("String  耗时："+ (end-start));
	}
	
	private static void getCurr2(){
		long start = System.currentTimeMillis();
		for(int i=0;i<100000000;i++){
			StringBuffer str = new StringBuffer().append("hello ").append("world!");
		}
		long end = System.currentTimeMillis();
		System.out.println("StringBuffer  耗时："+ (end-start));
	}
	
	private static void getString(String s1,String s2){
		long start = System.currentTimeMillis();
		String s3;
		for(int i=0;i<100000000;i++){
			s3 = s1+s2;
		}
		long end = System.currentTimeMillis();
		System.out.println("String  耗时："+ (end-start));
	}
	
	private static void getString2(String s1,String s2){
		long start = System.currentTimeMillis();
		for(int i=0;i<100000000;i++){
			new StringBuffer().append(s1).append(s2);
		}
		long end = System.currentTimeMillis();
		System.out.println("StringBuffer  耗时："+ (end-start));
	}
}
