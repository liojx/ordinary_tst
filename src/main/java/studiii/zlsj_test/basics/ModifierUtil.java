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
	
	 static void C(int up,int down) {
		 //边界条件
		 //边界条件不满足，继续递归
		 //边界条件满足，跳出递归
		 
		 // 这里边界条件： 当你的填充值fill == down 数值时跳出递归
		 // 
//		 int[] a = new int[down-up+fill];
//		 int idx =0;
//		 if(fill <= up) {
//			 for(int i=fill ;i<=(down-up+fill);i++) {
//				 a[idx++] = i;
//			 }
//		 }
		Map<Integer,List> map = new HashMap<Integer,List>();
		 for(int ac = 1;ac<=up;ac++) {
			 List<Integer> arr = new ArrayList<Integer>();
			 int indexOf = ac;
			 int cc = indexOf;
			 do {
				 arr.add(cc);
				 ++cc;
			 }while(cc <= (down-up+indexOf));// 例如C7选3，那么第1 位可以排 1到7-3+1 = 5，第2位 能排2到7-3+2=6，第3位只能排3到7-3+3=7
			 map.put(ac, arr);
		 }
		 for(int i =0;i<map.size();i++) {
			 List<Integer> l = map.get(i+1);
			 for(Integer j : l) {
//				 System.out.println(j);
			 }
		 }
		 
		 
		 /**笨办法选6位**/
		 List<Integer> l1 = map.get(1);		 
		 List<Integer> l2 = map.get(2);		 
		 List<Integer> l3 = map.get(3);	
		 List<Integer> l4 = map.get(4);	
		 List<Integer> l5 = map.get(5);	
		 List<Integer> l6 = map.get(6);	
		 List<int[]> all = new ArrayList<int[]>();
		 int idx1 = 0;
		 int idx2 = 0;
		 int idx3 = 0;
		 int idx4 = 0;
		 int idx5 = 0;
		 int idx6 = 0;
		 int zx = 0;
		 while(zx<l1.size()) {
			 idx1 = l1.get(zx);
			 int bx = 0;
			 while(bx<l2.size()) {
				 idx2 = l2.get(bx);
				 if(idx2 <= idx1) {
					 bx++;
					 continue;
				 }
				 int dx = 0;
				 while(dx<l3.size()) {
					 idx3 = l3.get(dx);
					 if(idx3 <= idx2 || idx3 <= idx1) {
						 dx++;
						 continue;
					 }
					int ex = 0;
					while(ex<l4.size()) {
						idx4 = l4.get(ex);
						if(idx4 <= idx3 || idx4 <= idx2 || idx4<=idx1) {
							ex++;
							continue;
						}
						int fx =0;
						while(fx<l5.size()) {
							idx5 = l5.get(fx);
							if(idx5 <= idx4  ||idx5 <= idx3 || idx5 <= idx2 || idx5<=idx1) {
								fx++;
								continue;
							}
							int gx = 0;
							while(gx<l6.size()) {
								idx6 = l6.get(gx);
								if(idx6 <=idx5 || idx5 <= idx4  ||idx5 <= idx3 || idx5 <= idx2 || idx5<=idx1) {
									gx ++;
									continue;
								}
								 int[] abc = new int[6];
								 abc[0] = idx1;
								 abc[1] = idx2;
								 abc[2] = idx3;
								 abc[3] = idx4;
								 abc[4] = idx5;
								 abc[5] = idx6;
								 all.add(abc);
								gx++;
							}
							fx++;
						}
						ex++;
					}
					 dx++;
				 }
				 bx++;
			 }
			 zx++;
		 }
//		 for(int[] aaa:all) {
//			 Logger.log(aaa[0] +"-"+aaa[1]+"-"+aaa[2] +"-"+aaa[3]+"-"+aaa[4]+"-"+aaa[5]);
////			 System.out.println(aaa[0] +"-"+aaa[1]+"-"+aaa[2] +"-"+aaa[3]+"-"+aaa[4]+"-"+aaa[5]);
//		 }
		 
		 // 批量存入库
		 int num = 0;
		 int total = all.size();
		 int n = 1;
		 int size = 100000;
		 List<int[]> wan = new ArrayList<int[]>();
		 List<int[]> wan2 = new ArrayList<int[]>();
		 for(int[] one : all) {
			 wan.add(one);
			 num ++;
			 if(num < size) {
				 if(total-(n*size)>0) {
					 continue;
				 }else {
					 wan2.add(one);
					 continue;
				 }
			 }
			 for(int j = 1;j<=16;j++) {
				 batchInsert(wan,j);
				 System.out.println("--------n*size="+n+"x"+"100000"+"******"+j);
			 }
			 wan.removeAll(wan);
			 num = 0;
			 n ++;
		 }
		 for(int j = 1;j<=16;j++) {
			 batchInsert(wan2,j);
			 System.out.println("wan2*size---"+wan2.size()+"------j="+j);
		 }
	}
	 
	static void batchInsert(List<int[]> arr,int blue) {
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "insert into two_color_ball (r1,r2,r3,r4,r5,r6,blue) value (?,?,?,?,?,?,?)";
		PreparedStatement prest = null;;
		try {
			prest = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (int i = 0; i < arr.size(); i++) {
				int[] one = arr.get(i);
				prest.setInt(1, one[0]);
				prest.setInt(2, one[1]);
				prest.setInt(3, one[2]);
				prest.setInt(4, one[3]);
				prest.setInt(5, one[4]);
				prest.setInt(6, one[5]);
				prest.setInt(7, blue);
				prest.addBatch();
			}
			prest.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(prest != null) {
				prest.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

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
		C(6,33);
		
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
