package studiii.zlsj_test.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liaosijun
 * @since 2018年10月16日 上午10:34:44
 */
public class CombineUtil {
	
	static List<Integer[]> list = new ArrayList<Integer[]>();
	/** 
	 * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1 
	 * @param n 
	 * @return 
	 */  
	private static long factorial(int n) {  
	    return (n > 1) ? n * factorial(n - 1) : 1;  
	} 
	
	/** 
	 * 计算组合数，即C(n, m) = n!/((n-m)! * m!) 
	 * @param n 
	 * @param m 
	 * @return 
	 */
	public static long combination(int n, int m) {  
	    return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;  
	}
	
	/** 
	 * 组合选择（从列表中选择n个组合） 
	 * @param dataList 待选列表 
	 * @param n 选择个数 
	 */ 
	public static void combinationSelect(Integer[] dataList, int n) {  
	    System.out.println(String.format("C(%d, %d) = %d", dataList.length, n, combination(dataList.length, n)));
	    combinationSelect(dataList, 0, new Integer[n], 0);  
	}  
	  
	/** 
	 * 组合选择 
	 * @param dataList 待选列表 
	 * @param dataIndex 待选开始索引 
	 * @param resultList 前面（resultIndex-1）个的组合结果 
	 * @param resultIndex 选择索引，从0开始 
	 */  
	private static void combinationSelect(Integer[] dataList, int dataIndex, Integer[] resultList, int resultIndex) {  
	    int resultLen = resultList.length;   // 被选择结果的长度
	    int resultCount = resultIndex + 1;   // 被选结果临时长度
	    if (resultCount > resultLen) { 		 // 全部选择完时，输出组合结果  
//	        System.out.println(Arrays.asList(resultList)); 
	    	Integer[] cp = new Integer[resultList.length];
	    	System.arraycopy(resultList, 0, cp, 0, resultList.length);
	        list.add(cp);
	        return;  
	    }  
	  
	    // 递归选择下一个  
	    // dataList.length + resultCount - resultLen 这一段意思是 C(7,3),7个中选3个的化，第1位只能选 7+1-3=5，也就是第一位只能选1-5，第二位只能从2-6，第三位只能从3-7来选
	    for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {  
	        resultList[resultIndex] = dataList[i];  
	        combinationSelect(dataList, i + 1, resultList, resultIndex + 1);  
	    }  
	} 
	
	public static void main(String[] args) {
		Integer [] s = {1,2,4,8,16,32,64,128,256,512,1024,2048};
		CombineUtil.combinationSelect(s,2);
		for(Integer[] a : CombineUtil.list) {
			for(Integer b : a) {
				System.out.print(b+" ");
			}
			System.out.println();
		}
	}
}
