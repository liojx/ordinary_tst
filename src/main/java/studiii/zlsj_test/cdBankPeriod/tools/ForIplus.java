package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.ArrayList;

public class ForIplus {
	public static ArrayList<String> patarr = new ArrayList<String>();
	
	public static void main(String[] args) {
		patarr.add("apple");
		patarr.add("orange");
		patarr.add("pare");
		patarr.add("melon");
		patarr.add("banana");
		for(int i=0;i<patarr.size();i++){
			boolean flag = false;
			String fruit = patarr.get(i);
			
			if(fruit !=null){
				flag = (fruit.equals("melon")||fruit.equals("orange"));
			}
			
			if(!flag){
				System.out.println("f>>"+i);
				patarr.remove(i);
				System.out.println(patarr);
				i--;
				System.out.println("b>>"+i);
			}
			System.out.println("i="+i);
		}
	}
}
